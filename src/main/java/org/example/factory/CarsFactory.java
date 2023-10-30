package org.example.factory;


import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.CoupeCarDecorator;
import org.example.factory.cars.concrete_cars.SedanCarDecorator;

public class CarsFactory {
    private static CarsFactory carsFactory;
    private String factoryName;

    public CarsFactory(String factoryName) {
        this.factoryName = factoryName;
    }
    public static CarsFactory getInstance(String factoryName) {
        if (carsFactory == null) {
            carsFactory = new CarsFactory(factoryName);
        }
        return carsFactory;
    }
    public String getFactoryName() {
        return factoryName;
    }

    public Car createCar(String type) { // create car with no specific options
        switch (type) {
            case "basic" -> {
                return new BasicCar();
            }
            case "sedan" -> {
                return new SedanCarDecorator(new BasicCar());
            }
            case "coupe" -> {
                return new CoupeCarDecorator(new BasicCar());
            }
            default -> {
                return null;
            }
        }
    }
    public Car createCar(String type, String brand, String model, int horsePower, double cost) { // create car with specific options
        switch (type) {
            case "basic" -> {
                return new BasicCar(brand, model, horsePower, cost);
            }
            case "sedan" -> {
                return new SedanCarDecorator(new BasicCar(brand, model, horsePower, cost));
            }
            case "coupe" -> {
                return new CoupeCarDecorator(new BasicCar(brand, model, horsePower, cost));
            }
            default -> {
                return null;
            }
        }
    }
}

