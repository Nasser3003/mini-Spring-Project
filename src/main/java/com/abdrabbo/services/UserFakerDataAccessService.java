package com.abdrabbo.services;

import com.abdrabbo.creator.Dao;
import com.github.javafaker.Faker;

import java.util.List;

public class UserFakerDataAccessService {


    public static void printFakeUsers (int numberOfUsers) {
        Faker faker = new Faker();
        int counter = 0;
        do {
            System.out.println("Name: " + faker.funnyName().name() + "\n" +
                    "Address: " + faker.address().country() + "\n");
            counter++;
        } while (counter <= numberOfUsers);
    }
}
