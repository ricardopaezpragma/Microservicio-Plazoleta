package com.pragma.plazoleta.infrastructure.exception;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String email) {
        super("El usuario con correo: " + email + " ya está registrado.");
    }
}
