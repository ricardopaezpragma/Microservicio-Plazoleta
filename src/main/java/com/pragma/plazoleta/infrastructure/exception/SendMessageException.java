package com.pragma.plazoleta.infrastructure.exception;

public class SendMessageException extends RuntimeException{
    public SendMessageException(String message) {
        super(message);
    }
}
