package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.OrderDishesRequestDto;
import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.handler.implementations.OrderHandlerImp;
import com.pragma.plazoleta.application.mapper.OrderDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

class OrderHandlerImpTest {
    @Mock
    private IOrderServicePort orderServicePort;
    @Mock
    private IDishServicePort dishServicePort;
    @Mock
    private OrderDtoMapper orderDtoMapper;
    @Mock
    private IUserServicePort userServicePort;
    @InjectMocks
    private OrderHandlerImp orderHandler;

    @BeforeEach
    public void setUp() {
       // orderHandler = new OrderHandlerImp(orderServicePort, dishServicePort, orderRequestDtoMapper, userServicePort);
    }

    @Test
    void testCreateOrder() {
        String email = "test@example.com";
        OrderRequestDto orderRequestDto = new OrderRequestDto();
        orderRequestDto.setRestaurantId(1);
        List<OrderDishesRequestDto> orderDishes = new ArrayList<>();
        orderRequestDto.setOrderDishes(orderDishes);

        int customerId = 1;
        int restaurantId = 1;

        Order order = new Order(1, null, null, null, null, null, null);

        when(userServicePort.getUserIdByEmail(email)).thenReturn(customerId);
        when(orderServicePort.getOrdersByCustomerId(customerId)).thenReturn(new ArrayList<>());
        when(dishServicePort.getDishById(anyInt())).thenReturn(new Dish(1, "BicMac", 2, "Hamburguesa", 15000, 1, "url", true));
        when(orderDtoMapper.toModel(orderRequestDto)).thenReturn(order);
        when(orderServicePort.saveOrder(order)).thenReturn(order.getId());


       // OrderHandlerImp orderHandler = new OrderHandlerImp(orderServicePort, dishServicePort, orderRequestDtoMapper, userServicePort);

        orderHandler.createOrder(email, orderRequestDto);

        verify(userServicePort, times(1)).getUserIdByEmail(email);
        verify(orderServicePort, times(1)).getOrdersByCustomerId(customerId);
        verify(dishServicePort, times(orderDishes.size())).getDishById(anyInt());
        verify(orderDtoMapper, times(1)).toModel(orderRequestDto);
        verify(orderServicePort, times(1)).saveOrder(order);
    }

    // Write additional test cases for the remaining scenarios in createOrder method
}
