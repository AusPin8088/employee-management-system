package com.backend.employeemanagementsystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException exception,
            HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                List.of());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler({DuplicateResourceException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ApiError> handleConflict(Exception exception, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                exception.getMessage(),
                request.getRequestURI(),
                List.of());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(apiError);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException exception,
            HttpServletRequest request) {
        List<ValidationError> validationErrors = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed",
                request.getRequestURI(),
                validationErrors);
        return ResponseEntity.badRequest().body(apiError);
    }
}
