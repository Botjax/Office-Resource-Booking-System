package com.capstone.officeresourcebooking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "resources")
public class Resource {

    public Resource() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "resource_id")
    private int id;

    @Column(name = "resource_name", nullable = false)
    private String resourceName;

    @Enumerated(EnumType.STRING)
    @Column(name = "resource_type", columnDefinition = "ENUM('room', 'equipment', 'vehicle')")
    private ResourceType resourceType;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "description")
    private String description;

    @Column(name = "is_available", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private boolean isAvailable;

    // Enum for ResourceType
    public enum ResourceType {
        ROOM,
        EQUIPMENT,
        VEHICLE
    }
}
