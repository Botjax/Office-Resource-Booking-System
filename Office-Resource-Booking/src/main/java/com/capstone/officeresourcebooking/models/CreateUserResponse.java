package com.capstone.officeresourcebooking.models;

import lombok.Getter;
import lombok.Setter;

import java.util.regex.Pattern;

@Getter
@Setter
public class CreateUserResponse {
    public enum CreateStatus {
        INVALID_EMAIL,
        EMAIL_TAKEN,
        SUCCESS,
        FAILURE,
    }
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private CreateStatus status;

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }
    public CreateUserResponse(CreateStatus status) {
        this.status = status;
    }
}