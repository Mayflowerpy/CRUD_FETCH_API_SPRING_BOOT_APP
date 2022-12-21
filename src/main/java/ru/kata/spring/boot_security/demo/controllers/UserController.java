package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String printWelcome() {
        return "home";
    }

    @GetMapping("/admin")
    public String pageForAdmins() {
        return "admin";
    }

    @GetMapping("/user")
    public String pageForUser() {
        return "user";
    }
}
