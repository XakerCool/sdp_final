package org.example.saloon.shop;

public class Client {
    private String phoneNumber;
    private String email;

    public Client() {
        this.phoneNumber = "";
        this.email = "";
    }
    public Client(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getDescription() {
        return "phone number: " + this.getPhoneNumber() + ", and email: " + this.getEmail();
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
