package org.example.saloon.shop;

public class Order {
    private Client client;

    public Order(Client client) {
        this.client = client;
    }
    public String setOrder(String carBrand, String carModel) {
        return "Client with: " + this.client.getDescription() + " ordered a car with brand " + carBrand + " model " + carModel;
    }
}
