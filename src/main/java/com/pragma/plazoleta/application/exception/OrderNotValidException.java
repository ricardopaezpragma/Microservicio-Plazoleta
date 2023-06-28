package com.pragma.plazoleta.application.exception;

public class OrderNotValidException extends RuntimeException {
    public OrderNotValidException(int dishId,int restaurantId) {
        super("El plato con id: "+dishId+" no peretenece al restaruante: "+restaurantId);
    }
    public OrderNotValidException() {
        super("Cantidad minima de un producto debe ser 1");
    }
}
