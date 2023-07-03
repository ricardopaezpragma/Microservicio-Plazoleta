package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.dto.OrderResponseDto;
import com.pragma.plazoleta.application.exception.CustomerAlreadyHasAnOrderException;
import com.pragma.plazoleta.application.exception.OrderNotValidException;
import com.pragma.plazoleta.application.mapper.OrderDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Order;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderHandlerImp implements IOrderHandler {
    private final IOrderServicePort orderServicePort;
    private final IDishServicePort dishServicePort;
    private final OrderDtoMapper orderDtoMapper;
    private final IUserServicePort userServicePort;
    private final IEmployeeServicePort employeeServicePort;

    @Override
    public Integer createOrder(String email, OrderRequestDto orderRequestDto) {
        int customerId = userServicePort.getUserIdByEmail(email);
        int restaurantId = orderRequestDto.getRestaurantId();

        orderServicePort.getOrdersByCustomerId(customerId)
                .stream()
                .filter(order -> order.getStatus().equalsIgnoreCase("PENDIENTE"))
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
        Order order = orderDtoMapper.toModel(orderRequestDto);
        order.setCustomerId(customerId);
        order.setDate(LocalDate.now());
        order.setStatus("PENDIENTE");
        return orderServicePort.saveOrder(order);
    }

    @Override
    public Page<OrderResponseDto> getOrdersByStatus(String email, String status, int page, int size) {
        int userId = userServicePort.getUserIdByEmail(email);
        int restaurantId = employeeServicePort.getEmployeeByUserId(userId).getRestaurantId();
        return orderDtoMapper.toResponsePage(orderServicePort.getOrdersByStatusAndRestaurantId(status.toUpperCase(), restaurantId, page, size));
    }

    @Override
    public List<OrderResponseDto> setOrderInMaking(String email, List<Integer> ordersIdList) {
        int userId = userServicePort.getUserIdByEmail(email);
        int restaurantId = employeeServicePort.getEmployeeByUserId(userId).getRestaurantId();
        return ordersIdList
                .stream()
                .map(this.orderServicePort::getOrderByOrderId)
                .filter(order -> order.getStatus().equalsIgnoreCase("PENDIENTE"))
                .peek(order -> {
                    if (restaurantId != order.getRestaurantId()) {
                        throw new OrderNotValidException(order.getId(),restaurantId);
                    }
                    order.setStatus("EN_PREPARACION");
                    order.setChefId(userId);
                    orderServicePort.updateOrder(order);
                })
                .map(this.orderDtoMapper::toResponse)
                .toList();
    }
}
