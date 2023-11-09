package org.example.factory;


import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.CoupeCarDecorator;
import org.example.factory.cars.concrete_cars.SedanCarDecorator;

public abstract class CarsFactory {

    public abstract Car createCar();

    public abstract Car createCar(String brand, String model, int horsePower, double cost, String carId);

}

