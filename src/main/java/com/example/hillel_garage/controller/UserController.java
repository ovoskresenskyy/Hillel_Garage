package com.example.hillel_garage.controller;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.model.User;
import com.example.hillel_garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @GetMapping("/add_new")
    public String addNewUser(Model model) {
        User user = User.builder().build();
        model.addAttribute("user", user);
        return "newUser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update_user/{id}")
    public String updateUser(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "updateUser";
    }

//    @DeleteMapping("/{id}")
//    public User deleteUser(@PathVariable int id) {
//        return userService.deleteUser(id);
//    }
//
//    @PostMapping("/{userID}/car")
//    public Car addUsersCar(@PathVariable int userID, @RequestBody Car car) {
//        return userService.addUsersCar(userID, car);
//    }
//
//    @GetMapping("/{userID}/cars")
//    public List<Car> getAllCars(@PathVariable int userID) {
//        return userService.getCars(userID);
//    }
}
