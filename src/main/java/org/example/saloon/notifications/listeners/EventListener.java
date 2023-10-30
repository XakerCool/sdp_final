package org.example.saloon.notifications.listeners;

import org.example.factory.cars.Car;
import org.example.saloon.payment.PayStrategy;

public interface EventListener {
    void update(String eventType, Car car);
    void update(String eventType, PayStrategy pay);
}
