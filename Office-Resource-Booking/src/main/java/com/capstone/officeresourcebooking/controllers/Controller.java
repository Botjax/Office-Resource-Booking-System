package com.capstone.officeresourcebooking.controllers;

import com.capstone.officeresourcebooking.models.Login;
import com.capstone.officeresourcebooking.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.capstone.officeresourcebooking.services.*;

@RestController
@RequestMapping("/api")
public class Controller {
    private final UserService userService;

    public Controller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/helloworld")
    public String helloworld() {
        return "Hello World";
    }

    @PostMapping("/user/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
    }

    @PostMapping("/user/login")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public boolean loginUser(@RequestBody Login login) {
        return userService.verifyLogin(login.email, login.password);
    }
}
