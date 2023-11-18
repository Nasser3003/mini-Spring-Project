package com.abdrabbo.Dao;

import com.abdrabbo.car.Car;
import com.abdrabbo.car.MOTOR;
import com.abdrabbo.creator.Dao;
import com.github.javafaker.Bool;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CarDao implements Dao<Car> {

    @Override
    public List<Car> createObjectsFromFile(String filePath) {

        List<Car> allCars = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                Car car = new Car(lineSplit[0], lineSplit[1],
                        MOTOR.valueOf(lineSplit[2]), Integer.parseInt(lineSplit[3]), Boolean.parseBoolean(lineSplit[4]));
                allCars.add(car);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return allCars;
    }
}

