package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.spi.IOrderPersistencePort;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderDishesEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderDishesEntityRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderRepository;
import lombok.RequiredArgsConstructor;

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
    public List<Order> getOrdersByCustomerId(int customerId) {
        return orderEntityMapper.toDomainList(orderRepository.findByCustomerId(customerId));
    }
}
