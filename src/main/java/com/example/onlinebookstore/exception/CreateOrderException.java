package com.example.onlinebookstore.exception;

public class CreateOrderException extends RuntimeException {
    public CreateOrderException(String message, Exception exception) {
        super(message,exception);
    }

    public CreateOrderException(String message) {
        super(message);
    }
}
