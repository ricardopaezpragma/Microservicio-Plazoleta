package com.pragma.plazoleta.infrastructure.exception;

public class DishNotFoundException extends RuntimeException{
    public DishNotFoundException(int dishId) {
        super("No se encontró ningún plato con id: "+dishId);
    }
}
