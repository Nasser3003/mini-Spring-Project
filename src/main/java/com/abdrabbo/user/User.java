package com.abdrabbo.user;

import com.abdrabbo.car.Car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private static int totalUsersCount = 0;
    private String id;
    private String name;
    private GENDER gender;
    private List<Car> carsBooked = new ArrayList<>();

    {
        totalUsersCount++;
    }

    public User(String id, String name, GENDER gender, List<Car> carsBooked) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.carsBooked = carsBooked;
    }

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static int getTotalUsersCount() {
        return totalUsersCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) &&
                gender == user.gender && Objects.equals(carsBooked, user.carsBooked);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, carsBooked);
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Car> getCarsBooked() {
        return carsBooked;
    }

    public void setCarsBooked(List<Car> carsBooked) {
        this.carsBooked = carsBooked;
    }

    public String getId() {
        return id;
    }

    public GENDER getGender() {
        return gender;
    }

    public void setGender(GENDER gender) {
        this.gender = gender;
    }

}
