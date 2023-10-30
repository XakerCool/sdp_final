package org.example.saloon.payment;

import org.example.saloon.shop.Client;

public class PayByCard implements PayStrategy {
    private Client client;
    public PayByCard(Client client) {
        this.client = client;
    }
    @Override
    public String pay(int paymentAmount, int cost) {
        if (paymentAmount >= cost) {
            return "Client with " + client.getDescription() + " paid " + paymentAmount + "$ by card";
        }
        return "Client with " + client.getDescription() + " don't have enough money on card to pay";
    }
    @Override
    public String getPaymentType() {
        return "card";
    }
}