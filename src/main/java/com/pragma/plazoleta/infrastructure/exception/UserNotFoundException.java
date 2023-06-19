package com.pragma.plazoleta.infrastructure.exception;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
    private String email;
    private int userId;

    public UserNotFoundException(int userId) {
        this.userId = userId;
    }

    public UserNotFoundException(String email) {
        this.email = email;
    }
}
