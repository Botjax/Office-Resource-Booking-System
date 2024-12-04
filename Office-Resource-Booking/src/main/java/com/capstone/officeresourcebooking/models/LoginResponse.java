package com.capstone.officeresourcebooking.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    public enum LoginStatus {
        SUCCESS,
        FAILURE,
        INVALID_CREDENTIALS
    }

    private LoginStatus status;
    private String sessionId;

    // Constructor for Success with session ID
    public LoginResponse(LoginStatus status, String sessionId) {
        this.status = status;
        this.sessionId = sessionId;
    }

}
