package com.capstone.officeresourcebooking.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Getter
public class Reservation {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int reservationId;

    @Column(name = "user_id", nullable = false)
    private int userId;

    @Column(name = "resource_id", nullable = false)
    private int resourceId;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReservationStatus status;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum ReservationStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
    }

    public Reservation() {}

    public Reservation(int resourceId, LocalDateTime startTime, LocalDateTime endTime, ReservationStatus status, LocalDateTime createdAt) {
        this.resourceId = resourceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Reservation(LocalDateTime startTime, LocalDateTime endTime, String roomName, int resourceId, int userId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = ReservationStatus.PENDING; // Default status
        this.createdAt = LocalDateTime.now();    // Automatically set createdAt
        this.resourceId = resourceId;
        this.userId = userId;
    }
}
