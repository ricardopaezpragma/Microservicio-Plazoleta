package com.pragma.plazoleta.application.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OrderResponseDto {
    private Integer id;
    private Integer customerId;
    private LocalDate date;
    private String status;
    private Integer chefId;
    private Integer restaurantId;
    private List<OrderDishesRequestDto> orderDishes;
}
