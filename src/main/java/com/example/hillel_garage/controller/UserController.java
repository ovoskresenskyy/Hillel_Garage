package com.example.hillel_garage.controller;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.model.User;
import com.example.hillel_garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @PutMapping
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PutMapping("/{id}/car")
    public Car addCar(@PathVariable int id, @RequestBody Car car){
        return userService.addUsersCar(userService.getUser(id), car);
    }

    @PostMapping
    public User updateUser(@RequestBody User user){
        return null;
    }
}
