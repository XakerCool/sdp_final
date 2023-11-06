package org.example.factory.cars.concrete_cars;

import org.example.factory.cars.Car;
import org.example.factory.cars.CarDecorator;

public class CoupeCarDecorator extends CarDecorator {
    public CoupeCarDecorator(Car car) {
        super(car);

    }
    @Override
    public String getDescription() {
        return this.decoratedCar.getDescription() +
                "\n##Type: coupe";
    }
    @Override
    public double getCost() {
        return this.decoratedCar.getCost() + 4000.0;
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
