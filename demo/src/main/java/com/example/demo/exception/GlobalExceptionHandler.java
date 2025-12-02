package com.example.demo.exception;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(
            GlobalExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(
            MethodArgumentNotValidException ex) {

        List<String> validationError = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> validationError.add(error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("success", false);
        response.put("message", validationError);
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(ConflictErrorException.class)
    public ResponseEntity<Map<String, String>> ConflictErrorException(
            ConflictErrorException ex) {
        log.warn("ConflictError{}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NotFoundErrorException.class)
    public ResponseEntity<Map<String, String>> NotFoundErrorException(
            NotFoundErrorException ex) {
        log.warn("NotFoundError{}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(InsertFailedException.class)
    public ResponseEntity<Map<String, String>> InsertFailedException(
           InsertFailedException ex) {
        log.warn("NotFoundError{}", ex.getMessage());
        Map<String, String> error = new HashMap<>();
        error.put("success", "false");
        error.put("message", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}
