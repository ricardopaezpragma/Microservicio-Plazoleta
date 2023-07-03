package com.pragma.plazoleta.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empleados")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeEntity {
    @EmbeddedId
    private EmployeeEntityPk id;

    @Column(name = "activo")
    private boolean isActive;
}
