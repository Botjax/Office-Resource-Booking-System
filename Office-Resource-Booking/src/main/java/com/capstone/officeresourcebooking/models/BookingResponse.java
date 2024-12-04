package com.capstone.officeresourcebooking.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingResponse {
    public enum RequestStatus {
        AVAILABLE,
        UNAVAILABLE,
        ERROR,
        SUCCESS,
    }
    private RequestStatus responseStatus;
}
