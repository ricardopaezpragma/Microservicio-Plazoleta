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
public class OrderDishesEntityPk implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "id_pedido")
    private Integer orderId;

    @Column(name = "id_plato")
    private Integer dishId;

}
