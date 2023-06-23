package com.pragma.plazoleta.application.exception;

public class UnauthorizedDishModificationException extends RuntimeException {
    public UnauthorizedDishModificationException(int userId, int dishId) {
        super("El usuario id: " + userId + " no es propietario del restaurante donde se encuentra el plato con id: " + dishId);
    }
}
