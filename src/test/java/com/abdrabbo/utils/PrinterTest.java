package com.abdrabbo.services.utils;

import com.abdrabbo.Dao.CarDao;
import com.abdrabbo.Dao.UserDao;
import com.abdrabbo.car.Car;
import com.abdrabbo.creator.Dao;
import com.abdrabbo.services.CarRentalSystem;
import com.abdrabbo.user.User;
import com.abdrabbo.utils.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrinterTest {

    private CarRentalSystem carRentalSystem;
    List<Car> allCars;
    List<User> allUsers;

    @BeforeEach
    void setUp() {
        Dao creatingCars = new CarDao();
        Dao creatingUsers = new UserDao();
        allCars = creatingCars.createObjectsFromFile(
                "E:/SS/From Scrach/src/main/resources/carLIST.csv");

        allUsers = creatingUsers.createObjectsFromFile(
                "E:/SS/From Scrach/src/main/resources/userLIST.csv");

        carRentalSystem = new CarRentalSystem(allCars,allUsers);
    }

    @Test
    void canPrinter() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Printer.printsIterable(allUsers);

        String lineSeparator = System.lineSeparator();

        String allUsersOutPut = outputStream.toString().trim();
        String allUsersExpectedOutput =
                "User{id='7e4b9220-a47a-45a7-a33b-7182ee0dc30e', name='Leila'}" + lineSeparator +
                        "User{id='0236e9db-8c46-45a1-8fef-718d12e271f3', name='Bond'}" + lineSeparator +
                        "User{id='43bf7ab5-1f20-4693-a4f0-7319a7926d66', name='Ali'}" + lineSeparator +
                        "User{id='1fda7774-b948-42fa-ad35-7eb1a7248e35', name='Samira'}";

        // reset the output Stream Capture
        outputStream.reset();

        String allCarsExpectedOutput =
                "Car{id='RegBMW', brand='BMW', price=100, availableForRent=true, motorType=Electric}"  + lineSeparator +
                        "Car{id='RegTESLA', brand='TESLA', price=90, availableForRent=false, motorType=Electric}"  + lineSeparator +
                        "Car{id='RegAUDI', brand='AUDI', price=110, availableForRent=false, motorType=Gas}"  + lineSeparator +
                        "Car{id='RegTOYOTA', brand='Toyota', price=70, availableForRent=true, motorType=Gas}";

        Printer.printsIterable(allCars);

        String allCarsOutPut = outputStream.toString().trim();


        assertEquals(allUsersExpectedOutput, allUsersOutPut);
        assertEquals(allCarsExpectedOutput, allCarsOutPut);

    }

}
