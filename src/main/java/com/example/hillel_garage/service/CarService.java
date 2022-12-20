package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CarService {
    private final List<Car> cars = new LinkedList<>();
    private int carCounter;

    public Car addCar(int userId, Car car) {
        car.setId(++carCounter);
        car.setOwnerID(userId);
        cars.add(car);
        return car;
    }

    public Car addCar(Car car) {
        return addCar(0, car);
    }

    public Car getCar(int id) {
        for (Car car : cars) if (car.getId() == id) return car;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID not found");
    }

    public List<Car> getAll() {
        return cars;
    }

    public Car updateCar(Car incomingData) {
        Car car = getCar(incomingData.getId());
        car.setModel(incomingData.getModel());
        car.setColor(incomingData.getColor());
        return car;
    }

    public Car deleteCar(int id) {
        Car car = getCar(id);
        cars.remove(car);
        return car;
    }

    public void deleteUsersCars(int ownerID) {
        cars.removeIf(car -> car.getOwnerID() == ownerID);
    }
}
