package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CarService {
    private final Map<Integer, List<Car>> carsByOwners = new HashMap<>();
    private int carCounter;

    public Car addCar(int userId, Car car) {
        car.setId(++carCounter);
        List<Car> cars = carsByOwners.getOrDefault(userId, new LinkedList<>());
        cars.add(car);
        carsByOwners.put(userId, cars);
        return car;
    }

    public Car addCar(Car car) {
        return addCar(0, car);
    }

    public Car getCar(int id) {
        for (Car car : getAll()) if (car.getId() == id) return car;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID not found");
    }

    public List<Car> getAll() {
        if (carsByOwners.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Car list is empty.");
        return carsByOwners.values()
                .stream()
                .flatMap(Collection::stream)
                .toList();
    }

    public List<Car> getAll(int userID) {
        return carsByOwners.entrySet()
                .stream()
                .filter(f -> f.getKey() == userID)
                .map(Map.Entry::getValue)
                .flatMap(Collection::stream)
                .toList();
    }

    public Car updateColor(int id, String color) {
        Car car = getCar(id);
        car.setColor(color);
        return car;
    }

    public Car deleteCar(int id) {
        Car car = getCar(id);
        getAll().remove(car);
        return car;
    }

    public void deleteUsersCars(int userID) {
        carsByOwners.remove(userID);
    }
}
