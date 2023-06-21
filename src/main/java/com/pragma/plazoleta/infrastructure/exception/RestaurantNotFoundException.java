package com.pragma.plazoleta.infrastructure.exception;

public class RestaurantNotFoundException extends RuntimeException {
    public RestaurantNotFoundException(int restaurantId) {
        super("El restaurante con id: " + restaurantId + " no existe.");
    }
}
