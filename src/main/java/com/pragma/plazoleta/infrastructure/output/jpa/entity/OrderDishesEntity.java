package com.pragma.plazoleta.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pedidos_platos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDishesEntity {

    @EmbeddedId
    private OrderDishesEntityPk id;

    @Column(name = "cantidad")
    private Integer quantity;

}
