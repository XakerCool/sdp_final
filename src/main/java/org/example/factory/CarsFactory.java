package org.example.factory;

import org.example.factory.cars.Car;

public abstract class CarsFactory {

    public abstract Car createCar();

    public abstract Car createCar(String brand, String model, int horsePower, double cost, String carId);

}

