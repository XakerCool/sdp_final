package org.example;

import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.UpgradableCar;
import org.example.factory_saloon_adapter.SaloonFactoryAdapter;
import org.example.saloon.notifications.EventManager;
import org.example.saloon.notifications.listeners.EmailNotificationListener;
import org.example.saloon.notifications.listeners.SMSNotificationListener;
import org.example.saloon.payment.PayByCard;
import org.example.saloon.payment.PayByCash;
import org.example.saloon.shop.Client;
import org.example.saloon.shop.ClientAccount;

import javax.xml.namespace.QName;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static SaloonFactoryAdapter saloonFactoryAdapter;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("login or register: ");
        String loginOrRegister = scanner.nextLine();

        ClientAccount clientAccount = new ClientAccount();

        switch (loginOrRegister) {
            case "login" -> {
                System.out.print("Input your user-id: ");
                clientAccount.login(Integer.parseInt(scanner.nextLine()));
            }
            case "register" -> {
                System.out.print("Input your name: ");
                String name = scanner.nextLine();
                System.out.print("Input your surname: ");
                String surname = scanner.nextLine();
                System.out.print("Input your phone number: ");
                String phoneNumber = scanner.nextLine();
                System.out.print("Input your email: ");
                String email = scanner.nextLine();
                System.out.println("New user was added to the system: user id is " + clientAccount.registerUser(name, surname, phoneNumber, email));
            }
            default -> {

            }
        }
        saloonFactoryAdapter = SaloonFactoryAdapter.getInstance(clientAccount);
        System.out.println("#####################");
        EventManager eventManager = new EventManager("create", "order");

        // Ask for notification preference
        System.out.print("Do you want to receive notifications about your orders? 1 - yes; 2 - no: ");
        int notificationChoice = Integer.parseInt(scanner.nextLine());

        if (notificationChoice == 1) {
            System.out.println("Select notification method: 1 - Email; 2 - SMS -> ");
            int notificationMethodChoice = Integer.parseInt(scanner.nextLine());
            if (notificationMethodChoice == 1) {
                // Use email notification
                EmailNotificationListener emailListener = new EmailNotificationListener(clientAccount.getClient().getEmail());
                eventManager.subscribe("order", emailListener);
            } else if (notificationMethodChoice == 2) {
                // Use SMS notification
                SMSNotificationListener smsListener = new SMSNotificationListener(clientAccount.getClient().getPhoneNumber());
                eventManager.subscribe("order", smsListener);
            }
        }

        int choice;
        while (true) {
            System.out.println("#####################");
            printMenu();
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.println("#####################");
                    createCar(scanner);
                }
                case 2 -> {
                    System.out.println("#####################");
                    viewCreatedCars();
                }
                case 3 -> {
                    System.out.println("#####################");
                    purchaseCar(scanner, clientAccount, eventManager);
                }
                case 4 -> {
                    System.out.println("#####################");
                    addCustomizations(scanner);
                }
                case 5 -> {
                    System.out.println("#####################");
                    System.out.println(clientAccount.getUserInfo());
                }
                case 6 -> {
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Create a car");
        System.out.println("2. View created cars");
        System.out.println("3. Purchase a car");
        System.out.println("4. Add more to the car");
        System.out.println("5. View client info");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createCar(Scanner scanner) {
        System.out.print("First of all, type the subtype of car: default, nondefault -> ");
        String subtype = scanner.nextLine();
        String type = "";
        if (!subtype.equals("default")) {
            System.out.print("Now input the type of car (sedan, coupe): ");
            type = scanner.nextLine();;
        } else {
            type = "basic";
        }
        System.out.print("Enter car brand: ");
        String make = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car horsepower: ");
        int horsepower = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        saloonFactoryAdapter.createCar(subtype, type, make, model, horsepower, 20000);
        System.out.println("Car created successfully.");
    }

    private static void viewCreatedCars() {
        if (saloonFactoryAdapter.getCarsInSaloon().isEmpty()) {
            System.out.println("No cars have been created yet.");
        } else {
            System.out.println("Created Cars:");
            for (Car createdCar : saloonFactoryAdapter.getCarsInSaloon()) {
                System.out.println("Car ID: " + createdCar.getCarId());
                System.out.println(createdCar.getDescription());
                System.out.println("--------------");
            }
        }
    }

    private static void addCustomizations(Scanner scanner) {
        if (saloonFactoryAdapter.getCarsInSaloon().isEmpty()) {
            System.out.println("No cars have been created yet.");
        } else {
            System.out.print("Enter the Car ID to add customizations: ");
            String carIdToAddMore = scanner.nextLine();
            Car carToAddMore = null;

            for (Car createdCar : saloonFactoryAdapter.getCarsInSaloon()) {
                if (createdCar.getCarId().equals(carIdToAddMore)) {
                    carToAddMore = createdCar;
                    break;
                }
            }

            if (carToAddMore != null) {
                System.out.print("Enter additional customizations or upgrades (separated by commas): ");
                String customizations = scanner.nextLine();

                // Add the customizations or upgrades to the car
                if (carToAddMore instanceof UpgradableCar) {
                    ((UpgradableCar) carToAddMore).addImprovements(customizations.split(","));
                    System.out.println("Customizations can only be added to upgradable cars.");
                } else {
                    System.out.println("Customizations added successfully.");
                }
            } else {
                System.out.println("Car not found with the given Car ID.");
            }
        }
    }

    private static void purchaseCar(Scanner scanner, ClientAccount clientAccount, EventManager eventManager) {
        if (saloonFactoryAdapter.getCarsInSaloon().isEmpty()) {
            System.out.println("No cars have been created yet.");
        } else {
            System.out.print("Enter the Car ID you want to purchase: ");
            String carIdToPurchase = scanner.nextLine();
            Car carToPurchase = null;

            for (Car createdCar : saloonFactoryAdapter.getCarsInSaloon()) {
                if (createdCar.getCarId().equals(carIdToPurchase)) {
                    carToPurchase = createdCar;
                    break;
                }
            }

            if (carToPurchase != null) {
                System.out.println("Choose the type of payment: ");
                System.out.println("1. Cash");
                System.out.println("2. Card");
                int paymentChoice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (paymentChoice) {
                    case 1:
                        // Payment by cash
                        int cost = (int) carToPurchase.getCost(); // Assuming cost is in integer format
                        String cashPayment = new PayByCash(clientAccount.getClient()).pay(cost, cost);
                        System.out.println(cashPayment);
                        saloonFactoryAdapter.OrderCar(carIdToPurchase, cashPayment);
                        System.out.println("________________________________________________ ");
                        System.out.println("Dear client you have received a notification");


                        // Notify the client about the purchase
                        eventManager.notify("order", carToPurchase);

                        break;

                    case 2:
                        // Payment by card
                        System.out.println("Choose the payment method : ");
                        System.out.println("1. Kaspi");
                        System.out.println("2. Halyk");
                        int cardPaymentChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        String cardPaymentMethod = (cardPaymentChoice == 1) ? "Kaspi" : "Halyk Bank";
                        int cardCost = (int) carToPurchase.getCost(); // Assuming cost is in integer format
                        String cardPayment = new PayByCard(clientAccount.getClient()).pay(cardCost, cardCost);
                        System.out.println(cardPayment);
                        System.out.println("Payment method: " + cardPaymentMethod);
                        System.out.println(" ");
                        saloonFactoryAdapter.OrderCar(carIdToPurchase, cardPaymentMethod);
                        System.out.println("________________________________________________ ");
                        System.out.println("Dear client you have received a notification");


                        // Notify the client about the purchase
                        eventManager.notify("order", carToPurchase);

                        break;

                    default:
                        System.out.println("Invalid payment choice. Please select a valid option.");
                        break;
                }


                // Remove the purchased car from the list of created cars
                clientAccount.addCarToList(carToPurchase);
                System.out.println("Car purchased successfully.");
            } else {
                System.out.println("Car not found with the given Car ID.");
            }
        }
    }

}