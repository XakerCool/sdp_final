package org.example.saloon.payment;

import org.example.saloon.shop.Client;

public class PayByCash implements PayStrategy {
    private Client client;
    public PayByCash(Client client) {
        this.client = client;
    }
    @Override
    public String pay(int paymentAmount, int cost) {
        if (paymentAmount >= cost) {
            return "Client with " + client.getDescription() + " paid " + paymentAmount + "$ by cash";
        }
        return "Client with " + client.getDescription() + " don't have enough cash to pay";
    }
    @Override
    public String getPaymentType() {
        return "cash";
    }
}
