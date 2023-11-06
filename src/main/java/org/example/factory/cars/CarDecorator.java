package org.example.factory.cars;

public abstract class CarDecorator implements Car {
    protected Car decoratedCar;
    public CarDecorator(Car car) {
        this.decoratedCar = car;
    }

    @Override
    public String getDescription() {
        return this.decoratedCar.getDescription();
    }
    @Override
    public double getCost() {
        return this.decoratedCar.getCost();
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
