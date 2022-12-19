package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Welcome!");
        messages.add("Please, click the link to go to the users table");
        model.addAttribute("messages", messages);
        return "index";
    }

    @GetMapping("/users")
    public String userTable(Model model) {
        model.addAttribute("usersList", userService.getUsersList());
        return "users";
    }

    @GetMapping("/users/{id}")
    public String getContactById(Model model, @PathVariable("id") long userId) {
            model.addAttribute("user", userService.getById(userId));
        return "user";
    }

    @GetMapping("/newUser")
    public String addNewUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping("/newUser")
    public String saveNewUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/edit")
    public String updateUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "edit";
    }

    @PatchMapping("/users/{id}/edit")
    public String update(@ModelAttribute("user") User user,
                         @PathVariable("id") long id) {
         userService.updateUser(id, user);
         return "redirect:/users";
    }

}
