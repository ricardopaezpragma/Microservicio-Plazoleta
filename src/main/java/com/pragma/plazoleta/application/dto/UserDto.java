package com.pragma.plazoleta.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDto {
    @NotBlank(message = "Nombre requerido.")
    private String name;

    @NotBlank(message = "Apellido requerido.")
    private String lastName;

    @NotBlank(message = "Cedula requerido")
    @Pattern(regexp = "\\d+",message = "Solo se permiten números")
    private String documentId;

    @NotBlank(message = "Celular requerido")
    @Size(min = 8, max = 13, message = "El número de celular debe tener entre 10 y 13 numeros.")
    @Pattern(regexp = "\\+?\\d+",message = "Solo se permiten números")
    private String cellPhone;

    @NotNull(message = "Fecha de nacimiento requerido")
    @Past(message = "Fecha de nacimiento invalida")
    private LocalDate dateBirth;

    @NotBlank(message = "Correo requerido")
    @Email(message = "Correo no valido")
    private String email;

    @NotBlank(message = "Contraseña  requerido")
    private String password;
}
