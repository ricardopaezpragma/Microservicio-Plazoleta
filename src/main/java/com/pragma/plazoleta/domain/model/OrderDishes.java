package com.pragma.plazoleta.domain.model;

public class OrderDishes {
    private Integer dishId;
    private int quantity;

    public OrderDishes(Integer dishId, int quantity) {
        this.dishId = dishId;
        this.quantity = quantity;
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
