package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    final UserService userService;

    @Autowired
    public UserController(UserService userService ) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String printWelcome(Model model) {
        List<String> messages = new ArrayList<>();
        messages.add("Welcome!");
        messages.add("");
        model.addAttribute("messages", messages);
        return "home";
    }

    @GetMapping("/authenticated")
    public String pageForAuthenticatedUsers(Principal principal) {
        return "secured page" + principal.getName();
    }

    @GetMapping("/admin")
    public String pageForAdmins() {
        return "Admins page";
    }

    @GetMapping("/user")
    public String pageForUser() {
        return "Users page";
    }
}
