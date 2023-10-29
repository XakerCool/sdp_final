package org.example.factory.cars_details;

public class Engine  implements CarDetail{
    private String name;
    private int horsePower;
    private double volume;

    public Engine() {
        this.name = "undefined";
        this.horsePower = 0;
        this.volume = 0;
    }

    public Engine(String name, int horsePower, double volume) {
        this.name = name;
        this.horsePower = horsePower;
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getInfo() {
        return "Engine name: " + this.getName() + ", horse power: " + this.getHorsePower() + ", volume: " + this.getVolume();
    }
}
