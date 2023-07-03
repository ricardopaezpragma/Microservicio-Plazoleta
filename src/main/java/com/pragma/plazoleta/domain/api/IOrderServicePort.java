package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderServicePort {
    Integer saveOrder(Order order);
    Order getOrderByOrderId(int orderId);
    List<Order> getOrdersByCustomerId(int customerId);
    Page<Order> getOrdersByStatusAndRestaurantId(String status,int restaurantId, int page, int size);
    void updateOrder(Order order);

}
