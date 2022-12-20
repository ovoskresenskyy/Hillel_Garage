package com.example.hillel_garage.controller;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAll());
        return "cars/list";
    }

    @GetMapping("/add_new")
    public String addNewCar(Model model) {
        model.addAttribute("car", Car.builder().build());
        return "/cars/newcar";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/update_form/{id}")
    public String updateForm(@PathVariable(value = "id") int id,
                             Model model) {
        model.addAttribute("car", carService.getCar(id));
        return "cars/updatecar";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute("car") Car car) {
        carService.updateCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable(value = "id") int id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
}
