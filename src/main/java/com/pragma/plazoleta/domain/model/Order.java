package com.pragma.plazoleta.domain.model;

import com.pragma.plazoleta.domain.enums.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private Integer id;
    private Integer customerId;
    private LocalDate date;
    private OrderStatus status;
    private Integer chefId;
    private Integer restaurantId;
    private List<OrderDishes> orderDishes;

    public Order(Integer id, Integer customerId, LocalDate date, OrderStatus status, Integer chefId, Integer restaurantId, List<OrderDishes> orderDishes) {
        this.id = id;
        this.customerId = customerId;
        this.date = date;
        this.status = status;
        this.chefId = chefId;
        this.restaurantId = restaurantId;
        this.orderDishes = orderDishes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Integer getChefId() {
        return chefId;
    }

    public void setChefId(Integer chefId) {
        this.chefId = chefId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public List<OrderDishes> getOrderDishes() {
        return orderDishes;
    }

    public void setOrderDishes(List<OrderDishes> orderDishes) {
        this.orderDishes = orderDishes;
    }
}
