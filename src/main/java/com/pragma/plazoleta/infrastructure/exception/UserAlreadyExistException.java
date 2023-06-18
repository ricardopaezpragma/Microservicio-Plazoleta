package com.pragma.plazoleta.infrastructure.exception;

import lombok.Data;
import lombok.Getter;

@Getter
public class UserAlreadyExistException extends RuntimeException {
    private String email;
    public UserAlreadyExistException(String email) {
        this.email = email;
    }
}
