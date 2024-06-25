package com.vehiclejdbc;

public class Vehicle {
    private String make;
    private String model;
    private String type;
    private double rentalPrice;
    private double securityDeposit;
    private boolean rented;

    public Vehicle(String make, String model, String type, double rentalPrice, double securityDeposit) {
        this.make = make;
        this.model = model;
        this.type = type;
        this.rentalPrice = rentalPrice;
        this.securityDeposit = securityDeposit;
        this.rented = false;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getType() {
        return type;
    }

    public double getRentalPrice() {
        return rentalPrice;
    }

    public double getSecurityDeposit() {
        return securityDeposit;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRentalPrice(double rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public void setSecurityDeposit(double securityDeposit) {
        this.securityDeposit = securityDeposit;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public void rent() {
        rented = true;
    }

    public void returnVehicle() {
        rented = false;
    }

    public boolean needsService() {
        // Implement your logic to determine if the vehicle needs service
        return false;
    }

    public void extendTenure(int numberOfDays) {
        // Implement your logic to extend the tenure of the vehicle rental
    }
}
