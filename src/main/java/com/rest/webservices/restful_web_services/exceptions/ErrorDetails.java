package com.rest.webservices.restful_web_services.exceptions;

import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public class ErrorDetails {

    // timestamp
    // message
    // details

    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}

