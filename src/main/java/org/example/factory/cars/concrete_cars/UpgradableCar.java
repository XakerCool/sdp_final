package org.example.factory.cars.concrete_cars;

import org.example.factory.cars.Car;
import org.example.factory.cars.CarDecorator;

import java.util.ArrayList;
import java.util.List;

public class UpgradableCar extends CarDecorator {

    private List<String> improvements;

    public UpgradableCar(Car car) {
        super(car);
        this.improvements = new ArrayList<String>();
    }

    public void addImprovements(String ...upgrades) {
        for (String upgrade: upgrades) {
            if (!this.improvements.contains(upgrade)) {
                this.improvements.add(upgrade);
            }
        }
    }

    public String getFullDescription() {
        StringBuilder upgradesList = new StringBuilder();
        for (int i = 0; i < this.improvements.size(); i++) {
            upgradesList.append(i + 1).append(")").append(this.improvements.get(i)).append("\n");
        }
        return upgradesList.toString();
    }

    @Override
    public String getDescription() {
        return this.decoratedCar.getDescription() + "\n"  + this.getFullDescription();
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
