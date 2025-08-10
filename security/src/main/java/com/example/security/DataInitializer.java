package com.example.security;

import com.example.security.models.User;
import com.example.security.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

// src/main/java/com/example/demo/DataInitializer.java
@Component
public class DataInitializer {
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct // ← Ensures this runs on startup
    public void init() {
        userRepo.deleteAll(); // Clear old data (optional)
        userRepo.save(new User("user1", passwordEncoder.encode("pass1"), "admin"));
        userRepo.save(new User("user2", passwordEncoder.encode("pass2"), "user"));
        System.out.println("Users created: user1/pass1, user2/pass2"); // Custom log
    }
}