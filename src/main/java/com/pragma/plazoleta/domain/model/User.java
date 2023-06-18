package com.pragma.plazoleta.domain.model;

import java.time.LocalDate;

public class User {
    private String name;
    private String lastName;
    private String documentId;
    private String cellPhone;
    private LocalDate dateBirth;
    private String email;
    private String password;
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public User(String name, String lastName, String documentId, String cellPhone, LocalDate dateBirth, String email, String password, String role) {
        this.name = name;
        this.lastName = lastName;
        this.documentId = documentId;
        this.cellPhone = cellPhone;
        this.dateBirth = dateBirth;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
