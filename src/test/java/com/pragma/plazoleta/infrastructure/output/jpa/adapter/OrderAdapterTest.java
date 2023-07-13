package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.OrderDishes;
import com.pragma.plazoleta.infrastructure.exception.NotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderDishesEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderDishesEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderDishesEntityRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.plazoleta.manager.ManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class OrderAdapterTest {

    private OrderAdapter orderAdapter;

    @Mock
    private IOrderRepository orderRepository;

    @Mock
    private OrderEntityMapper orderEntityMapper;

    @Mock
    private IOrderDishesEntityRepository orderDishesEntityRepository;

    @Mock
    private OrderDishesEntityMapper orderDishesEntityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        orderAdapter = new OrderAdapter(
                orderRepository,
                orderEntityMapper,
                orderDishesEntityRepository,
                orderDishesEntityMapper
        );
    }

    @Test
    void saveOrder_shouldSaveOrderAndOrderDishes() {
        // Arrange
        Order order = ManagerFactory.crateOrder();
        OrderEntity orderEntity = ManagerFactory.createOrderEntity();
        Integer orderId = 1;
        List<OrderDishes> orderDishes = Arrays.asList(ManagerFactory.createOrderDishes());
        OrderDishesEntity orderDishesEntity = ManagerFactory.createOrderDishesEntity();
        order.setOrderDishes(orderDishes);

        when(orderEntityMapper.toEntity(order)).thenReturn(orderEntity);
        when(orderDishesEntityMapper.toEntity(any(OrderDishes.class))).thenReturn(orderDishesEntity);
        when(orderRepository.saveAndFlush(orderEntity)).thenReturn(orderEntity);
        // Act
        Integer result = orderAdapter.saveOrder(order);

        // Assert
        assertEquals(orderId, result);
        verify(orderRepository, times(1)).saveAndFlush(orderEntity);
        verify(orderDishesEntityMapper, times(orderDishes.size())).toEntity(any(OrderDishes.class));
        verify(orderDishesEntityRepository, times(orderDishes.size())).save(orderDishesEntity);
    }

    @Test
    void getOrderByOrderId_existingOrder_shouldReturnMappedOrder() {
        // Arrange
        int orderId = 1;
        OrderEntity orderEntity = ManagerFactory.createOrderEntity();
        Order order = ManagerFactory.crateOrder();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(orderEntity));
        when(orderEntityMapper.toDomain(orderEntity)).thenReturn(order);

        // Act
        Order result = orderAdapter.getOrderByOrderId(orderId);

        // Assert
        assertEquals(order, result);
        verify(orderDishesEntityRepository, times(1)).findByOrderId(orderId);
        verify(orderDishesEntityMapper, times(1)).toDomainList(anyList());
        assertEquals(order.getOrderDishes(), result.getOrderDishes());
    }

    @Test
    void getOrderByOrderId_nonExistingOrder_shouldThrowNotFoundException() {
        // Arrange
        int orderId = 123;
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> orderAdapter.getOrderByOrderId(orderId));
    }

}