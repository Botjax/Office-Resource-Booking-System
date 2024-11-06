package com.capstone.officeresourcebooking.services;

import com.capstone.officeresourcebooking.models.Credentials;
import com.capstone.officeresourcebooking.models.SessionData;
import com.capstone.officeresourcebooking.models.User;
import com.capstone.officeresourcebooking.repositories.UserRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static HashMap<String, SessionData> sessions = new HashMap<>();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Scheduled(cron = "0 */10 * * * *") // Run once every 10 minutes
    public void deleteExpiredTokens() {
        Instant tenMinutesAgo = Instant.now().minusSeconds(10 * 60); // 10 minutes in seconds
        sessions.entrySet().removeIf(entry -> entry.getValue().getCreatedAt().toInstant().isBefore(tenMinutesAgo));
    }

    public String verifyLogin(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (BCrypt.checkpw(password, user.getPasswordHash())) {
                String uuid = UUID.randomUUID().toString();
                sessions.put(uuid, new SessionData(email));
                return uuid;
            }
        }
        return ""; // User not found or password incorrect
    }

    public User saveUser(Credentials credentials) {
        String passwordHash = encryptPassword(credentials.password); // Hashing the password before sending it into the database for security
        User user = new User(credentials.email, passwordHash);
        return userRepository.save(user);
    }

    public String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(10));
    }
}