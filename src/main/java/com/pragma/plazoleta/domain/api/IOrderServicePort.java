package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Order;

import java.util.List;

public interface IOrderServicePort {
    Integer saveOrder(Order order);
    List<Order> getOrdersByCustomerId(int customerId);
}
