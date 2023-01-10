//package ru.kata.spring.boot_security.demo.controllers;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import ru.kata.spring.boot_security.demo.models.User;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//@Controller
//@RequestMapping("/api/user")
//public class UserController {
//
//    final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @GetMapping("/")
//    public String pageForUser() {
//        return "user";
//    }
//
//    @GetMapping("/viewUser")
//    @ResponseBody
//    public ResponseEntity<User> showUser(Authentication auth) {
////        return new ResponseEntity<>((User) auth.getPrincipal(), HttpStatus.OK);
//        return  new ResponseEntity<>(userService.getUserByEmail(auth.getName()).get(), HttpStatus.OK);
//    }
//}