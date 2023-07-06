package com.pragma.plazoleta.application.handler.implementations;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.dto.OrderResponseDto;
import com.pragma.plazoleta.application.exception.CustomerAlreadyHasAnOrderException;
import com.pragma.plazoleta.application.exception.OrderNotValidException;
import com.pragma.plazoleta.application.handler.interfaces.IOrderHandler;
import com.pragma.plazoleta.application.mapper.OrderDtoMapper;
import com.pragma.plazoleta.domain.api.*;
import com.pragma.plazoleta.domain.enums.OrderStatus;
import com.pragma.plazoleta.domain.model.CustomerMessage;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.Traceability;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final ISendMessageServicePort sendMessageServicePort;
    private final ITraceabilityServicePort traceabilityServicePort;

    @Override
    public Integer createOrder(String email, OrderRequestDto orderRequestDto) {
        int customerId = userServicePort.getUserIdByEmail(email);
        int restaurantId = orderRequestDto.getRestaurantId();

        orderServicePort.getOrdersByCustomerId(customerId)
                .stream()
                .filter(order -> order.getStatus() == OrderStatus.PENDIENTE ||
                        order.getStatus() == OrderStatus.EN_PREPARACION ||
                        order.getStatus() == OrderStatus.LISTO)
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
                    throw new OrderNotValidException("The dish with id " + dish.getDishId() +
                            " does not belong to restaurant: " + restaurantId);
                });
        orderRequestDto.getOrderDishes()
                .stream()
                .filter(dish -> dish.getQuantity() <= 0)
                .findFirst()
                .ifPresent(dish -> {
                    throw new OrderNotValidException("Orders with quantity 0 are not allowed");
                });
        Order order = orderDtoMapper.toModel(orderRequestDto);
        order.setCustomerId(customerId);
        order.setDate(LocalDate.now());
        order.setStatus(OrderStatus.PENDIENTE);
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
        int employeeId = userServicePort.getUserIdByEmail(email);
        int restaurantId = employeeServicePort.getEmployeeByUserId(employeeId).getRestaurantId();
        return ordersIdList
                .stream()
                .map(this.orderServicePort::getOrderByOrderId)
                .filter(order -> order.getStatus() == OrderStatus.PENDIENTE)
                .peek(order -> {
                    this.validRestaurantOrder(restaurantId, order.getRestaurantId());
                    order.setStatus(OrderStatus.EN_PREPARACION);
                    order.setChefId(employeeId);
                    orderServicePort.updateOrder(order);

                    String customerEmail = userServicePort.getUserById(order.getCustomerId()).getEmail();
                    traceabilityServicePort.saveTraceability(new Traceability(order.getId(),
                            order.getCustomerId(), customerEmail, LocalDateTime.now(), OrderStatus.PENDIENTE,
                            OrderStatus.EN_PREPARACION, employeeId, email));


                })
                .map(this.orderDtoMapper::toResponse)
                .toList();
    }

    @Override
    public void setOrderInReady(String email, int orderId) {
        int employeeId = userServicePort.getUserIdByEmail(email);
        int restaurantId = employeeServicePort.getEmployeeByUserId(employeeId).getRestaurantId();
        Order order = orderServicePort.getOrderByOrderId(orderId);
        this.validRestaurantOrder(restaurantId, order.getRestaurantId());
        if (order.getStatus() != OrderStatus.EN_PREPARACION) {
            throw new OrderNotValidException("The order with id " + orderId + " is not in preparation yet");
        }
        order.setStatus(OrderStatus.LISTO);
        orderServicePort.updateOrder(order);
        String phone = userServicePort.getUserById(order.getCustomerId()).getCellPhone();
        sendMessageServicePort.notifyCustomer(new CustomerMessage(order.getId(), phone));

        String customerEmail = userServicePort.getUserById(order.getCustomerId()).getEmail();
        traceabilityServicePort.saveTraceability(new Traceability(order.getId(),
                order.getCustomerId(), customerEmail, LocalDateTime.now(), OrderStatus.EN_PREPARACION,
                OrderStatus.LISTO, employeeId, email));
    }

    @Override
    public void setOrderInDelivered(String email, int securityPin) {
        int userId = userServicePort.getUserIdByEmail(email);
        int restaurantId = employeeServicePort.getEmployeeByUserId(userId).getRestaurantId();
       /* Order order = orderServicePort.getOrderByOrderId();
        this.validRestaurantOrder(restaurantId, order.getRestaurantId());*/
    }

    @Override
    public void cancelOrder(String email, int orderId) {
        int userId = userServicePort.getUserIdByEmail(email);
        Order order = orderServicePort.getOrderByOrderId(orderId);
        if (userId != order.getCustomerId()) {
            throw new OrderNotValidException("The order does not belong to the customer");
        }
        if (order.getStatus() != OrderStatus.PENDIENTE) {
            if (order.getStatus() == OrderStatus.CANCELADO) {
                throw new OrderNotValidException("The order has already been canceled");
            }
            throw new OrderNotValidException("Lo sentimos, tu pedido ya está en preparación y no puede cancelarse");
        }
        order.setStatus(OrderStatus.CANCELADO);
        orderServicePort.updateOrder(order);
    }

    private void validRestaurantOrder(int restaurantId, int orderRestaurantId) {
        if (restaurantId != orderRestaurantId) {
            throw new OrderNotValidException("The order with id: " + orderRestaurantId +
                    " does not belong to the restaurant with id: " + restaurantId);
        }
    }
}
