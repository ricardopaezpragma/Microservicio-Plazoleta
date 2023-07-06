package com.pragma.plazoleta.domain.model;

import com.pragma.plazoleta.domain.enums.OrderStatus;

import java.time.LocalDateTime;

public class Traceability {
    private Integer orderId;
    private Integer customerId;
    private String customerEmail;
    private LocalDateTime date;
    private OrderStatus previousState;
    private OrderStatus newState;
    private Integer employeeId;
    private String employeeEmail;

    public Traceability(Integer orderId, Integer customerId, String customerEmail, LocalDateTime date, OrderStatus previousState, OrderStatus newState, Integer employeeId, String employeeEmail) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.customerEmail = customerEmail;
        this.date = date;
        this.previousState = previousState;
        this.newState = newState;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OrderStatus getPreviousState() {
        return previousState;
    }

    public void setPreviousState(OrderStatus previousState) {
        this.previousState = previousState;
    }

    public OrderStatus getNewState() {
        return newState;
    }

    public void setNewState(OrderStatus newState) {
        this.newState = newState;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }
}
