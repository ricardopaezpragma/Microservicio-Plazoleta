package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.OrderRequestDto;

public interface IOrderHandler {
    Integer createOrder(String email,OrderRequestDto orderRequestDto);
}
