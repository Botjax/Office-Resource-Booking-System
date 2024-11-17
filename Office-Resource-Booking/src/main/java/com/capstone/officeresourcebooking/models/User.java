package com.capstone.officeresourcebooking.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.sql.Timestamp;

@Entity
@Table(name = "Users")
public class User {

    public User() {}

    public User(String email, String passwordHash) {
        this.email = email;
        this.passwordHash = passwordHash;
    }

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Getter
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('employee', 'admin') DEFAULT 'employee'")
    private Role role = Role.EMPLOYEE;  // Default value

    @Column(name = "created_at", updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP") // Default value
    private Timestamp createdAt;

    //getters, setters, and structs
    public enum Role {
        EMPLOYEE,
        ADMIN
    }
}
