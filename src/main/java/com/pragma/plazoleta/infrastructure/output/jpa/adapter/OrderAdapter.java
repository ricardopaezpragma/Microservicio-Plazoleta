package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.spi.IOrderPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderDishesEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderDishesEntityRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@RequiredArgsConstructor
public class OrderAdapter implements IOrderPersistencePort {
    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final IOrderDishesEntityRepository orderDishesEntityRepository;
    private final OrderDishesEntityMapper orderDishesEntityMapper;

    @Override
    public Integer saveOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.toEntity(order);
        Integer orderId = orderRepository.saveAndFlush(orderEntity).getId();
        order.getOrderDishes()
                .stream()
                .map(this.orderDishesEntityMapper::toEntity)
                .peek(orderDishesEntity -> orderDishesEntity.getId().setOrderId(orderId))
                .forEach(orderDishesEntityRepository::save);
        return orderId;
    }

    @Override
    public Order getOrderByOrderId(int orderId) {
        Order order = orderEntityMapper.toDomain(orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("No order found with id: " + orderId)));
        order.setOrderDishes(orderDishesEntityMapper.toDomainList(orderDishesEntityRepository.findByOrderId(orderId)));
        return order;
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderEntityMapper.toDomainList(orderRepository.findByCustomerId(customerId));
    }

    @Override
    public Page<Order> getOrdersByStatusAndRestaurantId(String status, int restaurantId, int page, int size) {
        return orderRepository.findByStatusAndRestaurantId(status, restaurantId, PageRequest.of(page, size))
                .map(orderEntityMapper::toDomain)
                .map(order -> {
                    order.setOrderDishes(orderDishesEntityMapper.toDomainList(orderDishesEntityRepository.findByOrderId(order.getId())));
                    return order;
                });
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.save(orderEntityMapper.toEntity(order));
    }
}
