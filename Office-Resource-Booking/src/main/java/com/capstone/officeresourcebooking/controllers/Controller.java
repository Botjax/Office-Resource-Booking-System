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
    private final ReservationService reservationService;

    public Controller(UserService userService, ResourceService resourceService, ReservationService reservationService) {
        this.userService = userService;
        this.resourceService = resourceService;
        this.reservationService = reservationService;
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
        return null;
    }

    @PostMapping("bookings/rooms/check-availability")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public BookingResponse checkAvailability(@RequestBody BookingRequest bookingRequest) {
        if (userService.verifyUser(bookingRequest.getSecurityToken().getToken())) {
            return reservationService.checkAvailability(bookingRequest);
        }
        return null;
    }

    @PostMapping("bookings/rooms/make-reservation")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public BookingResponse makeReservation(@RequestBody BookingRequest bookingRequest) {
        if (userService.verifyUser(bookingRequest.getSecurityToken().getToken())) {
            return reservationService.makeReservation(bookingRequest, resourceService.findResourceByName(bookingRequest.getRoomName()), userService.getUserIdByToken(bookingRequest.getSecurityToken().getToken()));
        }
        return null;
    }

    @PostMapping("dashboard/welcome")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public String findEmail(@RequestBody TokenResponse tokenID) {
        return userService.getUserEmail(tokenID.getToken());
    }

    @PostMapping("dashboard/event-list")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public List<Event> findEvents(@RequestBody TokenResponse tokenID) {
        Long userID = (long) userService.getUserIdByToken(tokenID.getToken());
        return reservationService.getUserEvents(userID);
    }

    @PostMapping("calendar/events")
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public List<Event> findMonthlyEvents(@RequestBody TokenResponse tokenID) {
        Long userID = (long) userService.getUserIdByToken(tokenID.getToken());
        return reservationService.getCalendarView(userID);
    }

    @GetMapping("/security/mak/encrypt/{password}") // Encrypt a password, useful for the DBA to insert an admin user into the database with an encrypted password
    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
    public String encryptPassword(@PathVariable String password) {
        return userService.encryptPassword(password);
    }
}
