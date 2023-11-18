package com.abdrabbo.car;

import java.util.Objects;

public class Car {

    private static int totalCarsCount = 0;

    private String id;
    private String brand;
    private int price;
    private boolean availableForRent = true;
    private MOTOR motorType;

    {
        totalCarsCount++;
    }

    public Car(String id, String brand, MOTOR motorType, int price) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.motorType = motorType;
    }

    public Car(String id, String brand, MOTOR motorType, int price, boolean availableForRent) {
        this.id = id;
        this.brand = brand;
        this.price = price;
        this.availableForRent = availableForRent;
        this.motorType = motorType;
    }

    public static void setTotalCarsCount(int totalCarsCount) {
        Car.totalCarsCount = totalCarsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return price == car.price && availableForRent == car.availableForRent &&
                Objects.equals(id, car.id) && Objects.equals(brand, car.brand) && motorType == car.motorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, brand, price, availableForRent, motorType);
    }

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", price=" + price +
                ", availableForRent=" + availableForRent +
                ", motorType=" + motorType +
                '}';
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public MOTOR getMotorType() {
        return motorType;
    }

    public void setMotorType(MOTOR motorType) {
        this.motorType = motorType;
    }

    public boolean isAvailableForRent() {
        return availableForRent;
    }

    public void setAvailableForRent(boolean availableForRent) {
        this.availableForRent = availableForRent;
    }

    public String getId() {
        return id;
    }
}
