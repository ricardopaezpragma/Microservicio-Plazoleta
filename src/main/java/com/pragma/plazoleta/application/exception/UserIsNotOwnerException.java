package com.pragma.plazoleta.application.exception;

public class UserIsNotOwnerException extends RuntimeException {

    public UserIsNotOwnerException(String name, String lastName) {
        super(name + " " + lastName + " " + " no es propietario.");
    }
}
