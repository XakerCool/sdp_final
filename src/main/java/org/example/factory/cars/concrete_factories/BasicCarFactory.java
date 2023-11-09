package org.example.factory.cars.concrete_factories;

import org.example.factory.CarsFactory;
import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;

public class BasicCarFactory extends CarsFactory {

    private final String type = "Basic";

    @Override
    public Car createCar() {
        return new BasicCar();
    }
    @Override
    public Car createCar(String brand, String model, int horsePower, double cost, String carId) {
        return new BasicCar(brand, model, horsePower, cost, carId);
    }
}
