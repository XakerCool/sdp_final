package org.example.factory_saloon_adapter;

import org.example.factory.*;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_factories.*;
import org.example.saloon.notifications.*;
import org.example.saloon.payment.*;
import org.example.saloon.shop.*;

import java.util.ArrayList;
import java.util.List;

public class SaloonFactoryAdapter {
    List<Car> carsInSaloon = new ArrayList<Car>();
    private ClientAccount clientAccount;
    private Order order;
    private CarsFactory factory;
    public EventManager events;
    public static SaloonFactoryAdapter saloonFactoryAdapter;
    public static SaloonFactoryAdapter getInstance(ClientAccount clientAccount) {
        if (saloonFactoryAdapter == null) {
            saloonFactoryAdapter = new SaloonFactoryAdapter(clientAccount);
        }
        return saloonFactoryAdapter;
    }

    private SaloonFactoryAdapter(ClientAccount clientAccount) {
        this.clientAccount = clientAccount;
        this.order = new Order(this.clientAccount.getClient());
        this.events = new EventManager("create", "order");
    }

    public void createCar(String subtype, String type, String brand, String model, int horsePower, double cost) {
        Car car = null;
        switch (type) {
            case "basic" -> {
                factory = new BasicCarFactory();
            }
            case "sedan" -> {
                factory = new SedanCarFactory();
            }
            case "coupe" -> {
                factory = new CoupeCarFactory();
            }
            default -> {

            }
        }
        if (subtype.equals("default")) {
            car = factory.createCar();
        } else {
            car = factory.createCar(brand, model, horsePower, cost, "CAR - " + carsInSaloon.size());
        }
        events.notify("create", car);
        carsInSaloon.add(car);
    }

    public Car OrderCar(String carId, String paymentType) {
        Car orderedCar = null;
        for (Car car: carsInSaloon) {
            if (car.getCarId().equals(carId)) {
                orderedCar = car;
            }
        }
        System.out.println(order.setOrder(orderedCar.getBrand(), orderedCar.getModel()));
        if (paymentType.equals("cash")) {
            events.notify("order", new PayByCash(this.clientAccount.getClient()));
        } else {
            events.notify("order", new PayByCard(this.clientAccount.getClient()));
        }
        carsInSaloon.remove(orderedCar);
        return orderedCar;
    }

    public void upgradeCar(String carId, String ...upgrades) {
        this.clientAccount.upgradeCar(carId, upgrades);
    }
    public List<Car> getCarsInSaloon() {
        return this.carsInSaloon;
    }
}
