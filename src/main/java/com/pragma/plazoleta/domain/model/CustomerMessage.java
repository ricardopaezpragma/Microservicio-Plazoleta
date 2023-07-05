package com.pragma.plazoleta.domain.model;

public class CustomerMessage {
    private Integer orderId;
    private String customerPhone;

    public CustomerMessage(Integer orderId, String customerPhone) {
        this.orderId = orderId;
        this.customerPhone = customerPhone;
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
}
