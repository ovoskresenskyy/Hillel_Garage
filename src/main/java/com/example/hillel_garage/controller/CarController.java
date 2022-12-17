package com.example.hillel_garage.controller;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public Car addCar(@RequestBody Car car){
        return carService.addCar(car);
    }

    @GetMapping("/{id}")
    public Car getCar(@PathVariable int id) {
        return carService.getCar(id);
    }

    @GetMapping
    public List<Car> getAll(){
        return carService.getAll();
    }

    @PutMapping("/{id}")
    public Car updateColor(@PathVariable int id, @RequestParam(name = "color") String color) {
        return carService.updateColor(id, color);
    }

    @DeleteMapping("/{id}")
    public Car deleteUser(@PathVariable int id) {
        return carService.deleteCar(id);
    }

}
