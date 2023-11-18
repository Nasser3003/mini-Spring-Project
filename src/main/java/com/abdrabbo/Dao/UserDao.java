package com.abdrabbo.Dao;

import com.abdrabbo.creator.Dao;
import com.abdrabbo.user.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {

    @Override
    public List<User> createObjectsFromFile(String filePath) {
        List<User> allUsers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSplit = line.split(",");
                User user = new User(lineSplit[0], lineSplit[1]);
                allUsers.add(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return allUsers;
    }
}
