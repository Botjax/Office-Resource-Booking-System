package com.capstone.officeresourcebooking.models;

public class Room {
    private int id;
    private String name;

    // Constructors
    public Room() {}

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

