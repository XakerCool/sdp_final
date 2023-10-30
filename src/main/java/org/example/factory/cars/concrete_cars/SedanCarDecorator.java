package org.example.factory.cars.concrete_cars;

import org.example.factory.cars.Car;
import org.example.factory.cars.CarDecorator;

public class SedanCarDecorator extends CarDecorator {
    public SedanCarDecorator(Car car) {
        super(car);
    }
    @Override
    public String getDescription() {
        return this.decoratedCar.getDescription() +
                "\n##Type: sedan";
    }
    @Override
    public double getCost() {
        return this.decoratedCar.getCost() + 2000.0;
    }
}
