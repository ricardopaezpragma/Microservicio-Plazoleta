package com.pragma.plazoleta.infrastructure.output.jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "restaurantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "direccion")
    private String address;

    @Column(name = "id_propietario")
    private Integer ownerId;

    @Column(name = "telefono")
    private String phone;

    @Column(name = "url_logo")
    private String urlLogo;

    @Column(name = "nit")
    private String nit;
}