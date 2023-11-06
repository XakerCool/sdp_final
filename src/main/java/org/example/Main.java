package org.example;

import org.example.factory.CarsFactory;
import org.example.factory.cars.BasicCar;
import org.example.factory.cars.concrete_cars.SedanCarDecorator;
import org.example.factory.cars.concrete_cars.UpgradableCar;
import org.example.factory_saloon_adapter.SaloonFactoryAdapter;
import org.example.saloon.notifications.listeners.EmailNotificationListener;
import org.example.saloon.notifications.listeners.SMSNotificationListener;
import org.example.saloon.shop.Client;
import org.example.saloon.shop.ClientAccount;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        ClientAccount clientAccount = new ClientAccount(new Client("88005553535", "asd@gmail.com"));
        BasicCar upgradableCar = new BasicCar("toyota", "camry", 100, 100.0, "super-camry");
        clientAccount.addCarToList(upgradableCar);
        clientAccount.upgradeCar("super-camry", "gps");
        System.out.println(clientAccount.getConcreteCarDescription("super-camry"));
    }
}