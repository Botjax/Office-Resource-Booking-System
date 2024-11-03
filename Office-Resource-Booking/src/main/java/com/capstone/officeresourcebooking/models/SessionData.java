package com.capstone.officeresourcebooking.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Getter
@Setter
public class SessionData {
    String sessionEmail;
    Timestamp createdAt;

    public SessionData(String sessionEmail) {
        this.sessionEmail = sessionEmail;
        this.createdAt = Timestamp.from(Instant.now());
    }
}
