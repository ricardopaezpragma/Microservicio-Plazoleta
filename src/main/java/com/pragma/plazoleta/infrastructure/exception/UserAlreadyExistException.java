package com.pragma.plazoleta.infrastructure.exception;

import lombok.Data;
import lombok.Getter;


public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("El usuario con correo: " + email + " ya está registrado.");
    }
}
