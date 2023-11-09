package org.example.saloon.shop;

public class Client {
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    public Client() {
        this.name = "";
        this.surname = "";
        this.phoneNumber = "";
        this.email = "";
    }
    public Client(String name, String surname, String phoneNumber, String email) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getDescription() {
        return "Name: " + this.getName() + " | Surname: " + this.getSurname() + " | Phone number: " + this.getPhoneNumber() + " | Email: " + this.getEmail();
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
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return this.surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }

}
