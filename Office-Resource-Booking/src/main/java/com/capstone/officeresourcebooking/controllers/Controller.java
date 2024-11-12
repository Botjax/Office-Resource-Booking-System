package com.capstone.officeresourcebooking.controllers;

import com.capstone.officeresourcebooking.models.CreateUserResponse;
import com.capstone.officeresourcebooking.models.Credentials;
import com.capstone.officeresourcebooking.models.LoginResponse;
import com.capstone.officeresourcebooking.models.User;
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

    @GetMapping("/helloworld") // Test mapping
    public String helloworld() {
        return "Hello World";
    }

    @PostMapping("/user/create") // Create user
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public CreateUserResponse createUser(@RequestBody Credentials credentials) {
        return userService.createUser(credentials);
    }

    @PostMapping("/user/login") // User login
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public LoginResponse loginUser(@RequestBody Credentials credentials) {
        return userService.verifyLogin(credentials.email, credentials.password);
    }

    @GetMapping("/security/mak/encrypt/{password}") // Encrypt a password, useful for the DBA to insert an admin user into the database with an encrypted password
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public String encryptPassword(@PathVariable String password) {
        return userService.encryptPassword(password);
    }
}
