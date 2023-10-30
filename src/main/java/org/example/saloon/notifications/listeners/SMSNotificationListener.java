package org.example.saloon.notifications.listeners;

import org.example.factory.cars.Car;
import org.example.saloon.payment.PayStrategy;

public class SMSNotificationListener implements EventListener {
    private String phoneNumber;

    public SMSNotificationListener(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public void update(String eventType, Car car) {
        System.out.println("SMS to " + phoneNumber + ": car with this options below was " + eventType + "ed\n" + car.getDescription());
    }
    @Override
    public void update(String eventType, PayStrategy pay) {
        System.out.println("SMS to " + phoneNumber + ": user " + eventType + "ed car with " + pay.getPaymentType());
    }
}
