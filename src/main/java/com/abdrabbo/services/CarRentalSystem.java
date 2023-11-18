package com.abdrabbo.services;

import com.abdrabbo.car.Car;
import com.abdrabbo.user.User;
import com.abdrabbo.utils.Printer;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CarRentalSystem {

    private List<Car> allCars;
    private List<User> allUsers;

    public CarRentalSystem(List<Car> allCars, List<User> allUsers) {
        this.allCars = allCars;
        this.allUsers = allUsers;
    }

    public void bookCar(Scanner scanner) {

        List<Car> availableCars = availableCars();
        Printer.printsIterable(availableCars);

        try {
            Car selectedCar;
            while (true) {
                String inputCarId = scanner.nextLine();
                System.out.print("Car Id ");

                selectedCar = availableCars().stream()
                        .filter(c -> c.getId().equals(inputCarId)).findFirst().orElse(null);

                if (selectedCar != null) {
                    System.out.println("Car Selected");
                    break;
                }
            }

            while (true) {
                viewAllUsers();
                System.out.print("User id: ");
                String inputUserId = scanner.nextLine();

                User selectedUser = allUsers.stream()
                        .filter(u -> u.getId().equals(inputUserId)).findFirst().orElse(null);

                if (selectedUser != null) {
                    selectedUser.getCarsBooked().add(selectedCar);
                    selectedCar.setAvailableForRent(false);
                    System.out.println("\nSuccessfully booked\n");
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Car> availableCars() {
        return allCars.stream()
                .filter(Car::isAvailableForRent).collect(Collectors.toList());
    }

    public List<Car> availableElectricCars() {
        return availableCars().stream().filter(
                c -> c.getMotorType().toString().equals("Electric")).collect(Collectors.toList());
    }

    public List<Car> availableGasCars() {
        return availableCars().stream()
                .filter(c -> (c.getMotorType().toString().equals("Gas"))).collect(Collectors.toList());
    }

    public void viewAllUsers() {
        Printer.printsIterable(allUsers);
    }

    public void viewAllCars() {
        Printer.printsIterable(allCars);
    }

    public void viewUserBookedCars(Scanner scanner) {
        scanner.nextLine();

        while (true) {
            viewAllUsers();
            String selectedUserid = scanner.nextLine();

            User selectedUser = allUsers.stream()
                    .filter(u -> u.getId().equals(selectedUserid)).findFirst().orElse(null);

            if (selectedUser.getCarsBooked().isEmpty()) {
                System.out.println("No booked Cars");
                break;
            } else {
                System.out.println(selectedUser.getCarsBooked());
                break;
            }
        }
    }

    public void viewAllBookings() {
        List<User> userWithBookings = allUsers.stream()
                .filter(u -> !u.getCarsBooked().isEmpty()).collect(Collectors.toList());

        if (userWithBookings.isEmpty())
            System.out.println("currently there are No users with booking");
        else
            Printer.printsIterable(userWithBookings);
    }
}