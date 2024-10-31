package com.capstone.officeresourcebooking.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TestController {
    @GetMapping("/helloworld")
    public String helloworld() {
        return "Hello World";
    }
}
