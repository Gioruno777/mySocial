package com.example.demo.exception;

public class NotFoundErrorException extends RuntimeException {
    public NotFoundErrorException(String message) {
        super(message);
    }
}
