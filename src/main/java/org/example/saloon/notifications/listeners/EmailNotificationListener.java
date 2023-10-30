package org.example.saloon.notifications.listeners;

import org.example.factory.cars.Car;
import org.example.saloon.payment.PayStrategy;

public class EmailNotificationListener implements EventListener{
    private String email;

    public EmailNotificationListener(String email) {
        this.email = email;
    }

    @Override
    public void update(String eventType, Car car) {
        System.out.println("Email to " + email + ": car with this options below was " + eventType + "ed\n" + car.getDescription());
    }
    @Override
    public void update(String eventType, PayStrategy pay) {
        System.out.println("Email to " + email + ": user " + eventType + "ed car with " + pay.getPaymentType());
    }
}
