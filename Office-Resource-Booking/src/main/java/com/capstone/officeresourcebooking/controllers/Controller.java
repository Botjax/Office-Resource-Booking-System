package com.capstone.officeresourcebooking.controllers;

import com.capstone.officeresourcebooking.models.*;
import org.springframework.web.bind.annotation.*;
import com.capstone.officeresourcebooking.services.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private final UserService userService;
    private final ResourceService resourceService;

    public Controller(UserService userService, ResourceService resourceService) {
        this.userService = userService;
        this.resourceService = resourceService;
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

    @PostMapping("/bookings/rooms/list")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public List<Room> getListOfRooms(@RequestBody TokenResponse tokenID) {
        if (userService.verifyUser(tokenID.getToken())) {
            return resourceService.getAllResources();
        }
        else {
            return null;
        }
    }

    @GetMapping("/security/mak/encrypt/{password}") // Encrypt a password, useful for the DBA to insert an admin user into the database with an encrypted password
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public String encryptPassword(@PathVariable String password) {
        return userService.encryptPassword(password);
    }
}
