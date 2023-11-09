package org.example;

import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.UpgradableCar;
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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a client account
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter your email: ");
        String email = scanner.nextLine();

        // Inside the Main class
        Client client = new Client(phoneNumber, email);
        ClientAccount clientAccount = new ClientAccount(client);
        EventManager eventManager = new EventManager("create", "order");

// Ask for notification preference
        System.out.println("Do you want to receive notifications about your orders? ");
        System.out.println("1. Yes");
        System.out.println("2. No");
        int notificationChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (notificationChoice == 1) {
            System.out.println("Select notification method: ");
            System.out.println("1. Email");
            System.out.println("2. SMS");
            int notificationMethodChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            if (notificationMethodChoice == 1) {
                // Use email notification
                EmailNotificationListener emailListener = new EmailNotificationListener(email);
                eventManager.subscribe("order", emailListener);
            } else if (notificationMethodChoice == 2) {
                // Use SMS notification
                SMSNotificationListener smsListener = new SMSNotificationListener(phoneNumber);
                eventManager.subscribe("order", smsListener);
            }
        }


// The rest of your code remains the same, and you can use the eventManager to notify listeners.

        List<Car> createdCars = new ArrayList<>();

        int choice;
        while (true) {
            printMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createCar(scanner, createdCars);
                    break;

                case 2:
                    viewCreatedCars(createdCars);
                    break;

                case 3:
                    purchaseCar(scanner, createdCars, clientAccount, eventManager);
                    break;

                case 4:
                    addCustomizations(scanner, createdCars);
                    break;

                case 5:
                    // Exit the program
                    System.out.println("Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please select a valid option.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Menu:");
        System.out.println("1. Create a car");
        System.out.println("2. View created cars");
        System.out.println("3. Purchase a car");
        System.out.println("4. Add more to the car");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void createCar(Scanner scanner, List<Car> createdCars) {
        System.out.print("Enter car brand: ");
        String make = scanner.nextLine();
        System.out.print("Enter car model: ");
        String model = scanner.nextLine();
        System.out.print("Enter car horsepower: ");
        int horsepower = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        Car car = new BasicCar(make, model, horsepower, 20000, "CAR-" + createdCars.size());
        createdCars.add(car);
        System.out.println("Car created successfully.");
    }

    private static void viewCreatedCars(List<Car> createdCars) {
        if (createdCars.isEmpty()) {
            System.out.println("No cars have been created yet.");
        } else {
            System.out.println("Created Cars:");
            for (Car createdCar : createdCars) {
                System.out.println("Car ID: " + createdCar.getCarId());
                System.out.println(createdCar.getDescription());
                System.out.println("--------------");
            }
        }
    }

    private static void addCustomizations(Scanner scanner, List<Car> createdCars) {
        if (createdCars.isEmpty()) {
            System.out.println("No cars have been created yet.");
        } else {
            System.out.print("Enter the Car ID to add customizations: ");
            String carIdToAddMore = scanner.nextLine();
            Car carToAddMore = null;

            for (Car createdCar : createdCars) {
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

    private static void purchaseCar(Scanner scanner, List<Car> createdCars, ClientAccount clientAccount, EventManager eventManager) {
        if (createdCars.isEmpty()) {
            System.out.println("No cars have been created yet.");
        } else {
            System.out.print("Enter the Car ID you want to purchase: ");
            String carIdToPurchase = scanner.nextLine();
            Car carToPurchase = null;

            for (Car createdCar : createdCars) {
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
                        String name= clientAccount.toString();
                        System.out.println(" ");
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
                createdCars.remove(carToPurchase);
                clientAccount.addCarToList(carToPurchase);
                System.out.println("Car purchased successfully.");
            } else {
                System.out.println("Car not found with the given Car ID.");
            }
        }
    }

}