package org.example.saloon.payment;

public interface PayStrategy {
    String pay(int paymentAmount, int cost);
    String getPaymentType();
}
