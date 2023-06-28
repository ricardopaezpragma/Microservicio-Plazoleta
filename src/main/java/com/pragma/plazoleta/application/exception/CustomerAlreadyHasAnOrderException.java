package com.pragma.plazoleta.application.exception;

public class CustomerAlreadyHasAnOrderException extends RuntimeException{
    public CustomerAlreadyHasAnOrderException(int orderId) {
        super("El usuario tiene pendiente el pedido: "+orderId);
    }
}
