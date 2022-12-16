package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CarService {
    private final Map<Integer, Car> cars = new HashMap<>();
    private int carCounter;

    public Car getCar(int id){
        for (Car car : cars.values()) if (car.getId() == id) return car;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Car with this ID not found");
    }

    public Car addCar(int userId, Car car){
        car.setId(carCounter++);
        cars.put(userId, car);
        return car;
    }

    public Car addCar(Car car){
        return addCar(-1, car);
    }

    public List<Car> getAll(){
        if (cars.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Car list is empty.");
        return cars.values()
                .stream()
                .toList();
    }

    public List<Car> getAll(int userID){
        if (cars.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT, "Car list is empty.");
        return cars.entrySet()
                .stream()
                .filter(EntrySet -> EntrySet.getKey() == userID)
                .map(Map.Entry::getValue)
                .toList();
    }

    public Car updateCar(Car car){
        return car;
    }

    public Car deleteCar(int id) {
        return getCar(id);
    }

    public void deleteCars(int userID) {
         cars.remove(userID);
    }
}
