package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class DishDto {
    @NotBlank(message = "Nombre requerido.")
    private String name;

    @NotNull(message = "Id de categoria requerido.")
    private Integer categoryId;

    @NotBlank(message = "Descripción requerida.")
    private String description;

    @NotBlank(message = "Precio requerido.")
    @Pattern(regexp = "^[1-9]\\d*$",message = "El precio tiene que ser mayor a 0 y ser un número entero.")
    private String price;

    private Integer restaurantId;

    @NotBlank(message = "Url de la imagen requerido.")
    private  String urlImage;
}
