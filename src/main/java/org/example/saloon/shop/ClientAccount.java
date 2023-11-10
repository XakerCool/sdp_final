package org.example.saloon.shop;

import netscape.javascript.JSObject;
import org.example.factory.cars.BasicCar;
import org.example.factory.cars.Car;
import org.example.factory.cars.concrete_cars.CoupeCarDecorator;
import org.example.factory.cars.concrete_cars.SedanCarDecorator;
import org.example.factory.cars.concrete_cars.UpgradableCar;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ClientAccount {
    private final String usersDirectoryPath = "src/main/java/org/example/saloon/users";
    private final File directory = new File(usersDirectoryPath);
    private Client client;
    private List<Car> clientCars;

    public ClientAccount() {
        clientCars = new ArrayList<Car>();
    }

    public ClientAccount(Client client) {
        this.client = client;
        clientCars = new ArrayList<Car>();
    }
    public String getUserInfo() {
        return this.client.getDescription();
    }
    public void login (int userId) {
        try {
            for (File file: directory.listFiles()) {
                if (file.getName().contains(String.valueOf(userId))) {
                    JSONParser jsonParser = new JSONParser();
                    FileReader reader = new FileReader(usersDirectoryPath + "/client-" + userId + ".json");
                    Object obj = jsonParser.parse(reader);
                    this.client = parseClient((JSONObject) obj);
                }
            }
        } catch (Exception e) {

        }
    }
    public Client parseClient(JSONObject client) {
        String name = (String) client.get("name");
        String surname = (String) client.get("surname");
        String phoneNumber = (String) client.get("phoneNumber");
        String email = (String) client.get("email");
        return new Client(name, surname, phoneNumber, email);
    }

    public int registerUser(String name, String surname, String phoneNumber, String email) {
        try {
            int userId = 0;
            if (directory.listFiles() != null) {
                userId = directory.listFiles().length;
            }

            JSONObject newUser = new JSONObject();
            newUser.put("id", userId);
            newUser.put("name", name);
            newUser.put("surname", surname);
            newUser.put("phoneNumber", phoneNumber);
            newUser.put("email", email);
            this.client = new Client(name, surname, phoneNumber, email);

            FileWriter file = new FileWriter("src/main/java/org/example/saloon/users/client-" + userId + ".json");
            file.write(newUser.toJSONString());
            file.flush();
            return userId;
        } catch (Exception e) {
        }
        return 0;
    }

    public void addCarToList(Car car) {
        clientCars.add(car);
    }
    public void fillClientCarsList(Car ...cars) {
        clientCars.addAll(Arrays.asList(cars));
    }

    public void upgradeCar(String carId, String ...upgrades) {
        UpgradableCar concreteCar = null;
        Car tmp = null;
        for (Car car: clientCars) {
            if (car.getCarId().equals(carId)) {
                concreteCar = new UpgradableCar(car);
                tmp = car;
            }
        }
        clientCars.remove(tmp);
        if (concreteCar != null) {
            concreteCar.addImprovements(upgrades);
            clientCars.add(concreteCar);
        }
    }

    public String getConcreteCarDescription(String carId) {
        for (Car car: clientCars) {
            if (car.getCarId().equals(carId)) {
                switch (car.getClass().toString().substring(car.getClass().toString().lastIndexOf('.') + 1)) {
                    case "BasicCar" -> {
                        return car.getDescription();
                    }
                    case "Sedan" -> {
                        SedanCarDecorator sedan = new SedanCarDecorator(car);
                        return sedan.getDescription();
                    }
                    case "Coupe" -> {
                        CoupeCarDecorator coupe = new CoupeCarDecorator(car);
                        return coupe.getDescription();
                    }
                    case "UpgradableCar" -> {
                        UpgradableCar upgradableCar = new UpgradableCar(car);
                        return upgradableCar.getDescription();
                    }
                }
            }
        }
        return "";
    }

    public Client getClient() {
        return this.client;
    }
}
