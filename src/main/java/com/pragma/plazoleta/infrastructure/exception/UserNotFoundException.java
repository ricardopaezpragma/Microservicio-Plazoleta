package com.pragma.plazoleta.infrastructure.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(int userId) {
        super("No existe ningun usuario con id: " + userId);
    }

    public UserNotFoundException(String email) {
        super("El usuario con correo: " + email + " no existe");
    }
}
