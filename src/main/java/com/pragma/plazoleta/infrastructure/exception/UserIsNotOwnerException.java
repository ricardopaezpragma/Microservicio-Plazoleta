package com.pragma.plazoleta.infrastructure.exception;

import lombok.Getter;

@Getter
public class UserIsNotOwnerException extends RuntimeException{

    private String errorMessage;
    public UserIsNotOwnerException(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
