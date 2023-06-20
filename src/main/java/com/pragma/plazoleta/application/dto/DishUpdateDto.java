package com.pragma.plazoleta.application.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DishUpdateDto(
        @NotNull(message = "Id requerido para modificar.")
        Integer id,
        @NotBlank(message = "Precio requerido.")
        @Pattern(regexp = "^[1-9]\\d*$",message = "El precio tiene que ser mayor a 0 y ser un número entero.")
        String price,
        @NotBlank(message = "Descripción requerida.")
        String description
) {
}
