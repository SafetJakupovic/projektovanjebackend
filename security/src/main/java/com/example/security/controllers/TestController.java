package com.example.security.controllers;



import com.example.security.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.security.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

// src/main/java/com/example/demo/AuthTestController.java
@RestController
public class TestController{

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is public! No auth needed.";
    }

    @GetMapping("/private")
    @PreAuthorize("hasRole('admin')")
    public String privateEndpoint() {
        return "You're authenticated!";
    }

    @GetMapping("/getusers")
    @PreAuthorize("hasRole('admin')")
    public List<String> getUsersEndpoint(){
        return userRepository.findAll().stream().map(User::getUsername).collect(Collectors.toList());
    }

}