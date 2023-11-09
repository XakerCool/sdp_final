package org.example.factory.cars.concrete_factories;

import org.example.factory.CarsFactory;
import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.SedanCarDecorator;

public class SedanCarFactory extends CarsFactory {

    private final String type = "Sedan";

    @Override
    public Car createCar() {
        return new SedanCarDecorator(new BasicCar());
    }
    @Override
    public Car createCar(String brand, String model, int horsePower, double cost, String carId) {
        return new SedanCarDecorator(new BasicCar(brand, model, horsePower, cost, carId));
    }
}
