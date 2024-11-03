package com.capstone.officeresourcebooking.services;

import com.capstone.officeresourcebooking.models.User;
import com.capstone.officeresourcebooking.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean verifyLogin(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            return user.getPassword().equals(password); // Ensure password comparison is secure in a real application
        }
        return false; // User not found or password incorrect
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }
}


