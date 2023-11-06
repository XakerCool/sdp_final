package org.example.factory_saloon_adapter;

import org.example.factory.CarsFactory;
import org.example.factory.cars.Car;
import org.example.saloon.notifications.EventManager;
import org.example.saloon.payment.PayByCard;
import org.example.saloon.payment.PayByCash;
import org.example.saloon.shop.Client;
import org.example.saloon.shop.Order;

public class SaloonFactoryAdapter {
    private Client client;
    private Order order;
    private CarsFactory factory;
    public EventManager events;

    public SaloonFactoryAdapter(Client client, CarsFactory factory) {
        this.client = client;
        this.order = new Order(client);
        this.factory = factory;
        this.events = new EventManager("create", "order");
    }

    public void OrderCar(String subtype, String type, String brand, String model, int horsePower, double cost, String carId, String paymentType) {
        System.out.println(order.setOrder(type, brand, model));
        if (paymentType.equals("cash")) {
            events.notify("order", new PayByCash(client));
        } else {
            events.notify("order", new PayByCard(client));
        }
        Car car = null;
        if (subtype.equals("default")) {
            car = factory.createCar(type);
        } else {
            car = factory.createCar(type, brand, model, horsePower, cost, carId);
        }
        events.notify("create", car);
    }

    public void upgradeCar() {

    }
}
