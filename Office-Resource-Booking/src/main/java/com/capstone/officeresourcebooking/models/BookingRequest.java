package com.capstone.officeresourcebooking.models;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookingRequest {
    private TokenResponse securityToken;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public BookingRequest(TokenResponse securityToken, String roomName, LocalDateTime startTime, LocalDateTime endTime) {
        this.securityToken = securityToken;
        this.roomName = roomName;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public BookingRequest() {

    }
}
