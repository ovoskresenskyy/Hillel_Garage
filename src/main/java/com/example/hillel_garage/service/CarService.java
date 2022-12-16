package com.example.hillel_garage.service;

import com.example.hillel_garage.model.Car;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class CarService {
    private final Map<Integer, Car> cars = new HashMap<>();
    private int carCounter;

    public Car getCar(int id){
        for (Car car : cars.values()) {
            if (car.getId() == id) return car;
        }
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
}
