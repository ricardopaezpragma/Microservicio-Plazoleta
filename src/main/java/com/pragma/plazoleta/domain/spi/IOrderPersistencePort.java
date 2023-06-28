package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Order;

import java.util.List;

public interface IOrderPersistencePort {
    Integer saveOrder(Order order);
    List<Order> getOrdersByCustomerId(int customerId);

}
