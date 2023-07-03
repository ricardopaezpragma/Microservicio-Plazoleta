package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.spi.IOrderPersistencePort;
import org.springframework.data.domain.Page;

import java.util.List;

public class OrderUseCase implements IOrderServicePort {
    private final IOrderPersistencePort orderPersistencePort;

    public OrderUseCase(IOrderPersistencePort orderPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
    }

    @Override
    public Integer saveOrder(Order order) {
       return orderPersistencePort.saveOrder(order);
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderPersistencePort.getOrdersByCustomerId(customerId);
    }

    @Override
    public Page<Order> getOrdersByStatusAndRestaurantId(String status,int restaurantId, int page, int size) {
        return orderPersistencePort.getOrdersByStatusAndRestaurantId(status,restaurantId,page,size);
    }
}
