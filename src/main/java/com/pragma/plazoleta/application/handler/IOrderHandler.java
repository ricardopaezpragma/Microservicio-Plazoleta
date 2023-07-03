package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.dto.OrderResponseDto;
import org.springframework.data.domain.Page;

public interface IOrderHandler {
    Integer createOrder(String email,OrderRequestDto orderRequestDto);

    Page<OrderResponseDto>getOrdersByStatus(String email,String status,int page, int size);
}
