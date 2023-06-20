package com.pragma.plazoleta.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "platos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "id_categoria")
    private Integer categoryId;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "precio")
    private Integer price;

    @Column(name = "id_restaurante")
    private Integer restaurantId;

    @Column(name = "url_imagen")
    private String urlImage;

    @Column(name = "activo")
    private boolean isActive;
}
