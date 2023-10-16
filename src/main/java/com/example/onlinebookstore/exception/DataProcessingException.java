package com.example.onlinebookstore.exception;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Exception exception) {
        super(message,exception);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}