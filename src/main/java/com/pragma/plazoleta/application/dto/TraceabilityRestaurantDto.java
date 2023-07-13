package com.pragma.plazoleta.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TraceabilityRestaurantDto {
    private Integer orderId;
    private String duration;

}
