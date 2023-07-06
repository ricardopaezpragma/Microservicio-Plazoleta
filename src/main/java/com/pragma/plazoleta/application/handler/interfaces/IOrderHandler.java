package com.pragma.plazoleta.application.handler.interfaces;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.dto.OrderResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IOrderHandler {
    Integer createOrder(String email, OrderRequestDto orderRequestDto);

    Page<OrderResponseDto> getOrdersByStatus(String email, String status, int page, int size);

    List<OrderResponseDto> setOrderInMaking(String email, List<Integer> ordersIdList);

    void setOrderInReady(String email, int orderId);

    void setOrderInDelivered(String email, int securityPin);
    void cancelOrder(String email,int orderId);
}
