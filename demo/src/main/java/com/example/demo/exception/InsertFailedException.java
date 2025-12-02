package com.example.demo.exception;

public class InsertFailedException extends RuntimeException{
    public InsertFailedException (String message) {
        super(message);
    }
}
