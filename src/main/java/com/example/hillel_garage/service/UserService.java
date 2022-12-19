package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final Map<User, List<Car>> usersAndCars = new HashMap<>();
    private int userCounter;
    private final CarService carService;

    @Autowired
    public UserService(CarService carService) {
        this.carService = carService;
    }

    public User addUser(User user) {
        user.setId(++userCounter);
        usersAndCars.put(user, new LinkedList<>());
        return user;
    }

    public User getUser(int id) {
        for (User user : usersAndCars.keySet()) if (user.getId() == id) return user;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with this ID not found");
    }

    public List<User> getAllUsers() {
        if (usersAndCars.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "User list is empty.");
        return usersAndCars.keySet()
                .stream()
                .toList();
    }

    public User updateAge(int id, int age) {
        User user = getUser(id);
        user.setAge(age);
        return user;
    }

    public User deleteUser(int id) {
        User user = getUser(id);
        usersAndCars.remove(user);
        carService.deleteUsersCars(id);
        return user;
    }

    public Car addUsersCar(int userID, Car car) {
        Car newCar = carService.addCar(userID, car);
        usersAndCars.get(getUser(userID)).add(newCar);
        return newCar;
    }

    public List<Car> getCars(int userID) {
        List<Car> cars = carService.getAllByOwner(userID);
        if (cars.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Car list is empty.");
        return cars;
    }
}
