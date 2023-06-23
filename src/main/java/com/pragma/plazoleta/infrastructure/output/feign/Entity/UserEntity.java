package com.pragma.plazoleta.infrastructure.output.feign.Entity;

import lombok.Data;

import java.time.LocalDate;
@Data
public class UserEntity {
    private Integer id;
    private String name;
    private String lastName;
    private String documentId;
    private String cellPhone;
    private LocalDate dateBirth;
    private String email;
    private String password;
    private String role;
}
