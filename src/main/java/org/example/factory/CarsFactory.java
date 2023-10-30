package org.example.factory;

public class CarsFactory {
    private static CarsFactory carsFactory;
    private String factoryName;

    public CarsFactory(String factoryName) {
        this.factoryName = factoryName;
    }
    public static CarsFactory getInstance(String factoryName) {
        if (carsFactory == null) {
            carsFactory = new CarsFactory(factoryName);
        }
        return carsFactory;
    }
    public String getFactoryName() {
        return factoryName;
    }


}

