package com.pragma.plazoleta.application.exception;

public class OrderNotValidException extends RuntimeException {
    public OrderNotValidException(String message) {
        super(message);
    }
}
