package com.pragma.plazoleta.infrastructure.output.jpa.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntityPk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Column(name = "id_restaurante")
    private Integer restaurantId;
    @Column(name = "id_usuario")
    private Integer userId;
}
