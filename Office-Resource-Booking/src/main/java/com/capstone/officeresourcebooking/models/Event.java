package com.capstone.officeresourcebooking.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {
    private String room;
    private String time;
    private String color;
    private String capacity;
    private String description;

    public Event(String room, String time, String color) {
        this.room = room;
        this.time = time;
        this.color = color;
        this.capacity = "";
        this.description = "";
    }

    public Event(String room, String time, String color, String capacity, String description) {
        this.room = room;
        this.time = time;
        this.color = color;
        this.capacity = capacity;
        this.description = description;
    }
}
