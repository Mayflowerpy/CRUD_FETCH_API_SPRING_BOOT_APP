package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class AdminRESTController {

    UserService userService;
    RoleService roleService;
    @Autowired
    public AdminRESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> showAllUsers() {
        return new ResponseEntity<>(userService.getUsersList(), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        return new ResponseEntity<>(userService.getById(id), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<HttpStatus> apiAddUser(@RequestBody @Valid User user,
                                                 BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {

        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


//    @GetMapping("/admin")
//    public String pageForAdmins(@ModelAttribute("user") User user, Model model, Principal principal) {
//        model.addAttribute("admin", userService.getUserByEmail(principal.getName()).get());
//        model.addAttribute("users", userService.getUsersList());
//        model.addAttribute("roles", roleService.getRoles());
//        return "admin";
//    }
//
//    @GetMapping("/user")
//    public User pageForUser(Principal principal) {
//        return userService.getUserByEmail(principal.getName()).get();
//    }
//
//    @PostMapping("/admin/newUser")
//    public String saveNewUser(@ModelAttribute("user") @Valid User user,
//                              BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "admin";
//        }
//        userService.addUser(user);
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/admin/{id}")
//    public String deleteUser(@PathVariable("id") long id) {
//        userService.deleteUser(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/admin/{id}")
//    public String updateUser(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getById(id));
//        model.addAttribute("allRoles", roleService.getRoles());
//        return "redirect:/admin";
//    }
//
//    @PatchMapping("/admin/{id}")
//    public String update(@ModelAttribute("user") @Valid User user,
//                         BindingResult bindingResult,
//                         @PathVariable("id") long id) {
//        if (bindingResult.hasErrors()) {
//            return "admin";
//        }
//        userService.updateUser(id, user);
//        return "redirect:/admin";
//    }
}