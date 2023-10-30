package org.example.factory.cars;

public class CarFactory {

    private CarsFactory carsFactory;

    public CarFactory(CarsFactory carsFactory) {
        this.carsFactory = carsFactory;
    }

    public Car createCar(String name, String country, int year, String color, int cost, String type) {
        Engine engine = new Engine();
        Transmission transmission = new Transmission();
        Wheels wheels = new Wheels();

        Car car = null;

        if (type.equals("Coupe")) {
            car = new Coupe(name, country, year, color, cost, engine, transmission, wheels);
        } else if (type.equals("Sedan")) {
            car = new Sedan(name, country, year, color, cost, engine, transmission, wheels);
        }

        if (car != null) {
            carsFactory.addCar(car);
        }

        return car;
    }
}
