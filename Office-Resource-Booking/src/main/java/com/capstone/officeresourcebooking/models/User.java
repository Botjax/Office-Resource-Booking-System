package com.capstone.officeresourcebooking.models;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;
import java.sql.Timestamp;

@Entity
@Table(name = "Users")
public class User {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")  // Match with the primary key column in the database
    private Long id;

    @Column(name = "username", nullable = false)
    private String name;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    public enum Role {
        EMPLOYEE,
        ADMIN
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('employee', 'admin') DEFAULT 'employee'")
    private Role role = Role.EMPLOYEE;  // Default value

    @Column(name = "created_at", updatable = false, insertable = false)
    private Timestamp createdAt;

}
