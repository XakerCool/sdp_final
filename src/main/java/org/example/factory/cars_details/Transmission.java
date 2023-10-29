package org.example.factory.cars_details;

public class Transmission implements CarDetail{
    private String type; // automatic, mechanical
    private int gearsCount;

    public Transmission() {
        this.type = "undefined";
        this.gearsCount = 0;
    }
    public Transmission(String type, int gearsCount) {
        this.type = type;
        this.gearsCount = gearsCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGearsCount() {
        return gearsCount;
    }

    public void setGearsCount(int gearsCount) {
        this.gearsCount = gearsCount;
    }

    @Override
    public String getInfo() {
        return "Transmission type: " + this.getType() + ", gears count: " + this.getGearsCount();
    }
}
