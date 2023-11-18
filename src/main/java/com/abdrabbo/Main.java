package com.abdrabbo;

import com.abdrabbo.Dao.CarDao;
import com.abdrabbo.Dao.UserDao;
import com.abdrabbo.car.Car;
import com.abdrabbo.creator.Dao;
import com.abdrabbo.services.CarRentalSystem;
import com.abdrabbo.services.UserFakerDataAccessService;
import com.abdrabbo.user.User;
import com.abdrabbo.utils.Contains;
import com.abdrabbo.utils.Printer;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Integer[] choices = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<String> options = Arrays.asList(
                "1️⃣- Book Car",
                "2️⃣- View User Booked Cars",
                "3️⃣- View All Bookings",
                "4️⃣- View Available Cars",
                "5️⃣- View Available Electric Cars",
                "6️⃣- View Available Gas Cars",
                "7️⃣- View all Users",
                "8️⃣- View all Cars",
                "9️⃣- Exit"

        );

//        UserFakerDataAccessService.printFakeUsers(20);

        Dao creatingCars = new CarDao();
        Dao creatingUsers = new UserDao();

        List<Car> allCars = creatingCars.createObjectsFromFile(

                "E:/SS/From Scrach/src/main/resources/carLIST.csv");

        List<User> allUsers = creatingUsers.createObjectsFromFile(
                "E:/SS/From Scrach/src/main/resources/userLIST.csv");

        CarRentalSystem mainProgram1 = new CarRentalSystem(allCars, allUsers);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            Printer.printsIterable(options);

            int input = scanner.nextInt();

            if (!Contains.contains(choices, input))
                System.out.println("\n Incorrect choice, please try again \n");

            else {
                switch (input) {
                    case 1 -> {
                        mainProgram1.bookCar(scanner);
                    }
                    case 2 -> {
                        mainProgram1.viewUserBookedCars(scanner);
                    }
                    case 3 -> {
                        mainProgram1.viewAllBookings();
                    }
                    case 4 -> {
                        if (mainProgram1.availableCars().isEmpty())
                            System.out.println("There are no Available Cars right now!");
                        else
                            Printer.printsIterable(mainProgram1.availableCars());
                    }
                    case 5 -> {
                        if (mainProgram1.availableElectricCars().isEmpty())
                            System.out.println("There are no Available Electric Cars right now!");
                        else
                            Printer.printsIterable(mainProgram1.availableElectricCars());
                    }
                    case 6 -> {
                        if (mainProgram1.availableGasCars().isEmpty())
                            System.out.println("There are no Available Gas Cars right now!");
                        else
                            Printer.printsIterable(mainProgram1.availableGasCars());
                    }
                    case 7 -> {
                        mainProgram1.viewAllUsers();
                    }
                    case 8 -> {
                        mainProgram1.viewAllCars();
                    }
                    case 9 -> {
                        System.out.println("Exiting Program!");
                        scanner.close();
                        return;
                    }
                }
            }
        }
    }
}