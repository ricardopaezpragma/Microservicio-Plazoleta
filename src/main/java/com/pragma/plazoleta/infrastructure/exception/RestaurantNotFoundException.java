package com.pragma.plazoleta.infrastructure.exception;

import lombok.Getter;

@Getter
public class RestaurantNotFoundException extends RuntimeException{
    private final int restaurantId;
    public RestaurantNotFoundException(int restaurantId) {
        this.restaurantId=restaurantId;
    }
}
