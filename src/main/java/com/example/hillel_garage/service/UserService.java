package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    private final List<User> users = new LinkedList<>();
    private int userCounter;
    private final CarService carService;

    @Autowired
    public UserService(CarService carService) {
        this.carService = carService;
    }

    public User addUser(User user) {
        user.setId(userCounter++);
        users.add(user);
        return user;
    }

    public User getUser(int id) {
        for (User user : users) if (user.getId() == id) return user;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this ID not found");
    }

    public List<User> getAllUsers() {
        if (users.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User list is empty.");
        return users;
    }

    public User updateUser(User userFromRequest) {
        User user = getUser(userFromRequest.getId());
        user.setAge(userFromRequest.getAge());
        return user;
    }

    public Car addUsersCar(int userID, Car car) {
        for (Car usersCar : carService.getAll(userID)) {
            if (usersCar.equals(car))
                throw new ResponseStatusException(HttpStatus.CONFLICT, "This car was already added.");
        }

        return carService.addCar(userID, car);
    }
}
