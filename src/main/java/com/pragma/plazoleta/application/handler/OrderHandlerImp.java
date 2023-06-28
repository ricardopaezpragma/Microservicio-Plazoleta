package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.exception.CustomerAlreadyHasAnOrderException;
import com.pragma.plazoleta.application.exception.OrderNotValidException;
import com.pragma.plazoleta.application.mapper.OrderRequestDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandlerImp implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IDishServicePort dishServicePort;
    private final OrderRequestDtoMapper orderRequestDtoMapper;
    private final IUserServicePort userServicePort;

    @Override
    public Integer createOrder(String email, OrderRequestDto orderRequestDto) {
        int customerId = userServicePort.getUserIdByEmail(email);
        int restaurantId = orderRequestDto.getRestaurantId();

        orderServicePort.getOrdersByCustomerId(customerId)
                .stream()
                .filter(order -> order.getStatus().equalsIgnoreCase("Pendiente"))
                .findFirst()
                .ifPresent(order -> {
                    throw new CustomerAlreadyHasAnOrderException(order.getId());
                });

        orderRequestDto.getOrderDishes()
                .stream()
                .filter(dish -> dishServicePort.getDishById(dish.getDishId())
                        .getRestaurantId() != restaurantId)
                .findFirst()
                .ifPresent(dish -> {
                    throw new OrderNotValidException(dish.getDishId(), restaurantId);
                });
        orderRequestDto.getOrderDishes()
                .stream()
                .filter(dish -> dish.getQuantity() <= 0)
                .findFirst()
                .ifPresent(dish -> {
                    throw new OrderNotValidException();
                });
        Order order = orderRequestDtoMapper.toModel(orderRequestDto);
        order.setCustomerId(customerId);
        order.setDate(LocalDate.now());
        order.setChefId(5); //QUEMADO
        order.setStatus("Pendiente");
        return orderServicePort.saveOrder(order);
    }
}
