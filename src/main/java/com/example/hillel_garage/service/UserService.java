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

    public Map<User, List<Car>> getUsersAndCars() {
        return usersAndCars;
    }

    public User updateUser(User incomingData) {
        User user = getUser(incomingData.getId());
        user.setName(incomingData.getName());
        user.setAge(incomingData.getAge());
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

    public Car deleteUsersCar(int userID, int carID) {
        Car car = carService.getCar(carID);
        usersAndCars.get(getUser(userID)).remove(car);
        carService.deleteCar(carID);
        return car;
    }

}
