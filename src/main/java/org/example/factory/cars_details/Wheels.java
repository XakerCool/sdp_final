package org.example.factory.cars_details;

public class Wheels  implements CarDetail{
    private String name;
    private String type; // for winter or summer
    private double radius;

    public Wheels() {
        this.name = "undefined";
        this.type = "undefined";
        this.radius = 0;
    }
    public Wheels(String name, String type, double radius) {
        this.name = name;
        this.type = type;
        this.radius = radius;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String getInfo() {
        return "Wheels name: " + this.getName() + ", type: " + this.getType() + ", radius: " + this.getRadius();
    }
}
