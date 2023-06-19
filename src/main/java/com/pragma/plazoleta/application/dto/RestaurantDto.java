package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RestaurantDto {
    @Pattern(regexp = "^(?!\\d+$)[a-zA-Z0-9]+$", message = "El nombre no puede estar vacio o compuesto solo de números.")
    private String name;

    @NotBlank(message = "Dirección requerida.")
    private String address;

    @NotNull(message = "Doc Identidad requerido.")
    private Integer ownerId;

    @NotBlank(message = "Celular requerido")
    @Size(min = 8, max = 13, message = "El número de celular debe tener entre 10 y 13 numeros.")
    @Pattern(regexp = "(^$|^(\\+)?[0-9]+$)", message = "Solo se permiten números")
    private String phone;

    @NotBlank(message = "url del logo requerida.")
    private String urlLogo;

    @Pattern(regexp = "\\d*", message = "Solo se permiten números")
    @NotBlank(message = "Nit del restaurante requerido.")
    private String nit;
}
