package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.model.User;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

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
        for (User user : users) {
            if (user.getId() == id) return user;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this ID not found");
    }

    public List<User> getAll(){
        if (users.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User list is empty.");
        return users;
    }

    public Car addUsersCar(User user, Car car) {
        for (Car usersCar : user.getCars()) {
            if (usersCar.equals(car)) return car;
        }
        Car newCar = carService.addCar(car);
        user.getCars().add(newCar);
        return newCar;
    }
}
