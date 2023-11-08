package org.example.saloon.shop;

import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.CoupeCarDecorator;
import org.example.factory.cars.concrete_cars.SedanCarDecorator;
import org.example.factory.cars.concrete_cars.UpgradableCar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClientAccount {
    private Client client;
    private List<Car> clientCars;

    public ClientAccount(Client client) {
        this.client = client;
        clientCars = new ArrayList<Car>();
    }

    public void addCarToList(Car car) {
        clientCars.add(car);
    }
    public void fillClientCarsList(Car ...cars) {
        clientCars.addAll(Arrays.asList(cars));
    }

    public void upgradeCar(String carId, String ...upgrades) {
        UpgradableCar concreteCar = null;
        Car tmp = null;
        for (Car car: clientCars) {
            if (car.getCarId().equals(carId)) {
                concreteCar = new UpgradableCar(car);
                tmp = car;
            }
        }
        clientCars.remove(tmp);
        if (concreteCar != null) {
            concreteCar.addImprovements(upgrades);
            clientCars.add(concreteCar);
        }
    }

    public String getConcreteCarDescription(String carId) {
        for (Car car: clientCars) {
            if (car.getCarId().equals(carId)) {
                switch (car.getClass().toString().substring(car.getClass().toString().lastIndexOf('.') + 1)) {
                    case "BasicCar" -> {
                        return car.getDescription();
                    }
                    case "Sedan" -> {
                        SedanCarDecorator sedan = new SedanCarDecorator(car);
                        return sedan.getDescription();
                    }
                    case "Coupe" -> {
                        CoupeCarDecorator coupe = new CoupeCarDecorator(car);
                        return coupe.getDescription();
                    }
                    case "UpgradableCar" -> {
                        UpgradableCar upgradableCar = new UpgradableCar(car);
                        return upgradableCar.getDescription();
                    }
                }
            }
        }
        return "";
    }

    public Client getClient() {
        return this.client;
    }
}
