package org.example.factory.cars;

public class BasicCar implements Car{
    private String brand;
    private String model;
    private int horsePower;
    private double cost;
    public BasicCar() {
        this.brand = "Basic";
        this.model = "Basic";
        this.horsePower = 0;
        this.cost = 0;
    }
    public BasicCar(String brand, String model, int horsePower, double cost) {
        this.brand = brand;
        this.model = model;
        this.horsePower = horsePower;
        this.cost = cost;
    }

    @Override
    public String getDescription() {
        return "#Car brand: " + this.getBrand() +
                "\n##Model: " + this.getModel() +
                "\n#Horse powers: " + this.getHorsePower() +
                "\n##Cost: " + this.getCost();
    }
    @Override
    public double getCost() {
        return this.cost;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
