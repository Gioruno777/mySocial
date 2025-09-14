package com.example.demo.exception;

public class ConflictErrorException extends RuntimeException {
    public ConflictErrorException(String message) {
        super(message);
    }
}
