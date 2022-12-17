package com.example.hillel_garage.controller;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return carService.getCar(id);
    }

    @GetMapping
    public List<Car> getAll(){
        return carService.getAll();
    }

    @PutMapping
    public Car updateCar(@RequestBody Car car) {
        return carService.updateCar(car);
    }

    @DeleteMapping("/{id}")
    public Car deleteUser(@PathVariable int id) {
        return carService.deleteCar(id);
    }

}
