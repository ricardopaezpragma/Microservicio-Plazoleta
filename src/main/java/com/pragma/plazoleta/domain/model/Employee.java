package com.pragma.plazoleta.domain.model;

public class Employee {
    private Integer restaurantId;
    private Integer userId;
    private boolean isActive;

    public Employee(Integer restaurantId, Integer userId, boolean isActive) {
        this.restaurantId = restaurantId;
        this.userId = userId;
        this.isActive = isActive;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
