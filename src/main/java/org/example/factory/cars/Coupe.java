package org.example.factory.cars;

import org.example.factory.cars_details.Engine;
import org.example.factory.cars_details.Transmission;
import org.example.factory.cars_details.Wheels;

public class Coupe implements Car{
    private String model;
    private String country;
    private int year;
    private String color;
    private int cost;
    private Engine engine;
    private Transmission transmission;
    private Wheels wheels;

    public Coupe() {
        this.model = "undefined";
        this.country = "undefined";
        this.year = 0;
        this.color = "undefined";
        this.cost = 0;
        this.engine = new Engine();
        this.transmission = new Transmission();
        this.wheels = new Wheels();
    }
    public Coupe(String name, String country, int year, String color, int cost) {
        this.model = name;
        this.country = country;
        this.year = year;
        this.color = color;
        this.cost = cost;
        this.engine = new Engine();
        this.transmission = new Transmission();
        this.wheels = new Wheels();
    }
    public Coupe(Engine engine, Transmission transmission, Wheels wheels) {
        this.model = "undefined";
        this.country = "undefined";
        this.year = 0;
        this.color = "undefined";
        this.cost = 0;
        this.engine = engine;
        this.transmission = transmission;
        this.wheels = wheels;
    }
    public Coupe(String name, String country, int year, String color, int cost, Engine engine, Transmission transmission, Wheels wheels) {
        this.model = name;
        this.country = country;
        this.year = year;
        this.color = color;
        this.cost = cost;
        this.engine = engine;
        this.transmission = transmission;
        this.wheels = wheels;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public Wheels getWheels() {
        return wheels;
    }

    public void setWheels(Wheels wheels) {
        this.wheels = wheels;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String getDescription() {
        return "# Car model: " + this.getModel() +
                "\n# Country: " + this.getCountry() +
                "\n# Year: " + this.getYear() +
                "\n# Color: " + this.getColor() +
                "\n# Cost: " + this.getCost() + "$" +
                "\n# Engine:" +
                "\n## " + this.engine.getInfo() +
                "\n# Transmission:" +
                "\n## " + this.transmission.getInfo() +
                "\n# Wheels:" +
                "\n## " + this.wheels.getInfo();

    }
}
