package com.abdrabbo.services;

import com.abdrabbo.Dao.CarDao;
import com.abdrabbo.Dao.UserDao;
import com.abdrabbo.car.Car;
import com.abdrabbo.creator.Dao;
import com.abdrabbo.user.User;
import com.abdrabbo.utils.Printer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CarRentalSystemTest {

    private CarRentalSystem carRentalSystem;
    List<Car> allCars;
    List<User> allUsers;

    @BeforeEach
    void setup() {

        Dao creatingCars = new CarDao();
        Dao creatingUsers = new UserDao();
        allCars = creatingCars.createObjectsFromFile(
                "E:/SS/From Scrach/src/main/resources/carLIST.csv");

        allUsers = creatingUsers.createObjectsFromFile(
                "E:/SS/From Scrach/src/main/resources/userLIST.csv");

        carRentalSystem = new CarRentalSystem(allCars,allUsers);

    }


    @Test
        // canViewUserBookedCars() and canViewAllBookings() are depending on this Test
    void canBookCar() {

        Printer.printsIterable(allCars);
        Printer.printsIterable(allUsers);

        String input = "RegBMW\n7e4b9220-a47a-45a7-a33b-7182ee0dc30e\n";

        String[] parts = input.split("\n");

        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        Scanner scanner = new Scanner(System.in);

        Car car = allCars.stream().filter(c -> c.getId().equals(parts[0])).findFirst().orElse(null);
        User user = allUsers.stream().filter(u -> u.getId().equals(parts[1])).findFirst().orElse(null);

        int carsBookedBefore = user.getCarsBooked().size();
        boolean isAvailableForRentBefore = car.isAvailableForRent();

        carRentalSystem.bookCar(scanner);
        scanner.close();

        int carsBookedAfter = user.getCarsBooked().size();
        boolean isAvailableForRentAfter = car.isAvailableForRent();


        assertEquals(carsBookedBefore + 1, carsBookedAfter);

        assertEquals(!isAvailableForRentBefore, isAvailableForRentAfter);
    }

    @Test
    void canAvailableCars() {

        int allCarsAfter = allCars.size();
        long isAvailableForRent = allCars.stream().filter(Car::isAvailableForRent).count();

        assertEquals(allCarsAfter - 2, isAvailableForRent);
    }

    @Test
    void canAvailableElectricCars() {
        int availableElectricCars = carRentalSystem.availableGasCars().size();
        assertEquals(1, availableElectricCars);
    }

    @Test
    void canAvailableGasCars() {
        int availableGasCars = carRentalSystem.availableGasCars().size();
        assertEquals(1, availableGasCars);
    }

    @Test
        // this test is depending on canBookCar() Test
    void canViewUserBookedCars() {

        // booking a car for a user
        canBookCar();

        String userId = "\n7e4b9220-a47a-45a7-a33b-7182ee0dc30e";
        InputStream in = new ByteArrayInputStream(userId.getBytes());
        System.setIn(in);
        Scanner scanner = new Scanner(System.in);

        // Capture the standard output (console)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // make the output
        carRentalSystem.viewUserBookedCars(scanner);
        scanner.close();

        //  The System.setOut(System.out); statement is used to restore the standard output stream (System.out)
        //  to its original state after it has been temporarily redirected using System.setOut().
        System.setOut(System.out);

        String[] linesPrinted = outputStream.toString().trim().split("\n");

        String lastLine = linesPrinted[linesPrinted.length - 1];

        String expectedOutput = "[Car{id='RegBMW', brand='BMW', price=100, availableForRent=false, motorType=Electric}]";

        assertEquals(expectedOutput, lastLine);


    }

    @Test
        // this test is depending on canBookCar() Test
    void canViewAllBookings() {

        // booking a car for a user
        canBookCar();

        // Capture the standard output (console)
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        carRentalSystem.viewAllBookings();

        //  The System.setOut(System.out); statement is used to restore the standard output stream (System.out)
        //  to its original state after it has been temporarily redirected using System.setOut().
        System.setOut(System.out);

        String output = outputStream.toString().trim();

        String expectedOutput = "User{id='7e4b9220-a47a-45a7-a33b-7182ee0dc30e', name='Leila'}";

        assertEquals(expectedOutput, output);

    }

}
