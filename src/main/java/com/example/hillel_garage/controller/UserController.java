package com.example.hillel_garage.controller;

import com.example.hillel_garage.model.Car;
import com.example.hillel_garage.model.User;
import com.example.hillel_garage.service.CarService;
import com.example.hillel_garage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CarService carService;

    @Autowired
    public UserController(UserService userService, CarService carService) {
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("usersAndCars", userService.getUsersAndCars());
        return "users/list";
    }

    @GetMapping("/add_new")
    public String addNewUser(Model model) {
        model.addAttribute("user", User.builder().build());
        return "/users/newuser";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/update_form/{id}")
    public String updateForm(@PathVariable(value = "id") int id,
                             Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/updateuser";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/{id}/adding_car_form")
    public String addingCarForm(@PathVariable(value = "id") int userID,
                             Model model) {
        model.addAttribute("user", userService.getUser(userID));
        model.addAttribute("car", Car.builder()
                .ownerID(userID)
                .build());
        return "users/users_car/newcar";
    }

    @PostMapping("/{id}/savecar")
    public String saveCar(@PathVariable(value = "id") int userID,
                          @ModelAttribute("car") Car car) {
        userService.addUsersCar(userID, car);
        return "redirect:/users";
    }

    @GetMapping("/car_update_form/{ownerID}/{id}")
    public String usersCarUpdateForm(@PathVariable(value = "ownerID") int ownerID,
                                     @PathVariable(value = "id") int id,
                             Model model) {
        model.addAttribute("user", userService.getUser(ownerID));
        model.addAttribute("car", carService.getCar(id));
        return "users/users_car/updateuserscar";
    }

    @PostMapping("/update_users_car")
    public String updateUsersCar(@ModelAttribute("car") Car car) {
        carService.updateCar(car);
        return "redirect:/users";
    }

    @GetMapping("/car_delete/{ownerID}/{id}")
    public String deleteUsersCar(@PathVariable(value = "ownerID") int ownerID,
                                 @PathVariable(value = "id") int id) {
        userService.deleteUsersCar(ownerID, id);
        return "redirect:/users";
    }


}
