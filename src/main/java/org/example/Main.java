package org.example;

import org.example.factory.CarsFactory;
import org.example.factory_saloon_adapter.SaloonFactoryAdapter;
import org.example.saloon.notifications.listeners.EmailNotificationListener;
import org.example.saloon.notifications.listeners.SMSNotificationListener;
import org.example.saloon.shop.Client;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Client client = new Client("88005553535", "asd@gmail.com");
        CarsFactory factory = CarsFactory.getInstance("Super cars");
        SaloonFactoryAdapter processor = new SaloonFactoryAdapter(client, factory);

        System.out.println("Hello to our car shop! We produce cars with our own factory and sell them to you!");
        System.out.print("First of all, would you like to get notifications? y/n: ");
        boolean isReceiveNotifications = scanner.nextLine().equals("y");
        if (isReceiveNotifications) {
            System.out.print("Would you like to receive sms or email: ");
            if (scanner.nextLine().equals("sms")) {
                processor.events.subscribe("order", new SMSNotificationListener(client.getPhoneNumber()));
                processor.events.subscribe("create", new SMSNotificationListener(client.getPhoneNumber()));
            } else {
                processor.events.subscribe("order", new EmailNotificationListener(client.getEmail()));
                processor.events.subscribe("create", new EmailNotificationListener(client.getEmail()));
            }
        }
        System.out.println("#######################");
        System.out.print("Now enter your payment type: ");
        String userPaymentType = scanner.nextLine();
        System.out.println("#######################");
        System.out.print("Would you like to order simple car? y/n: ");
        String userSubtype = scanner.nextLine();
        System.out.print("Enter type of car (sedan, coupe): ");
        String userType = scanner.nextLine();
        System.out.println("#######################");
        if (userSubtype.equals("y")) {
            processor.OrderCar("default", userType, "", "", 0, 0, userPaymentType);
        } else {
            System.out.print("Input car brand: ");
            String userCarBrand = scanner.nextLine();
            System.out.print("Input car model: ");
            String userCarModel = scanner.nextLine();
            System.out.print("Input car horse powers: ");
            int userCarHorsePowers = Integer.parseInt(scanner.nextLine());
            processor.OrderCar("no default", userType, userCarBrand, userCarModel, userCarHorsePowers, 10000, userPaymentType);
        }
    }
}