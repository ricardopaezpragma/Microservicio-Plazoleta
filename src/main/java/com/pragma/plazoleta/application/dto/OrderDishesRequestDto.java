package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderDishesRequestDto {
    @NotNull(message = "Plato requerido")
    private Integer dishId;

    @NotNull(message = "Cantidad requerida")
    @Size(min = 1, message = "La cantidad minima es 1")
    private Integer quantity;
}
