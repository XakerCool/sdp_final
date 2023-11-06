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
                "\n#A#Type: sedan";
    }
    @Override
    public double getCost() {
        return this.decoratedCar.getCost() + 2000.0;
    }
    @Override
    public String getBrand() {
        return this.decoratedCar.getBrand();
    }
    @Override
    public String getModel() {
        return this.decoratedCar.getModel();
    }
    @Override
    public int getHorsePower() {
        return this.decoratedCar.getHorsePower();
    }
    @Override
    public String getCarId() {
        return this.decoratedCar.getCarId();
    }
}
