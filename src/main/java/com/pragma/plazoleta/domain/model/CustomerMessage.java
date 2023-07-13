package com.pragma.plazoleta.domain.model;

public class CustomerMessage {
    private Integer orderId;
    private String customerPhone;
    private String pin;

    public CustomerMessage(Integer orderId, String customerPhone, String pin) {
        this.orderId = orderId;
        this.customerPhone = customerPhone;
        this.pin = pin;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
