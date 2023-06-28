package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class OrderRequestDto {
    @NotNull(message = "Restaurante requerido")
    private Integer restaurantId;

    @NotNull(message = "Platos requeridos")
    private List<OrderDishesRequestDto> orderDishes;
}
