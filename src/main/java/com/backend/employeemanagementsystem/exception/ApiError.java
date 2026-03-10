package com.backend.employeemanagementsystem.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiError {

    private final LocalDateTime timestamp;
    private final int status;
    private final String error;
    private final String message;
    private final String path;
    private final List<ValidationError> validationErrors;

    public ApiError(int status, String error, String message, String path, List<ValidationError> validationErrors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.validationErrors = validationErrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public List<ValidationError> getValidationErrors() {
        return validationErrors;
    }
}
