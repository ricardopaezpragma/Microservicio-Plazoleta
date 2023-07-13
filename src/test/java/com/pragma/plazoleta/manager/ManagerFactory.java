package com.pragma.plazoleta.manager;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.domain.model.*;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.*;

import java.time.LocalDate;

public class ManagerFactory {
    public static Employee createEmployee() {
        return new Employee(1, 1, true);
    }

    public static EmployeeEntity createEmployeeEntity() {
        return new EmployeeEntity(new EmployeeEntityPk(1, 1), true);
    }

    public static Order crateOrder() {
        return new Order(1, 1, LocalDate.now(), null, 1, 1, null);
    }

    public static OrderDishes createOrderDishes() {
        return new OrderDishes(2, 2);
    }

    public static OrderDishesEntity createOrderDishesEntity() {
        OrderDishesEntity orderDishesEntity = new OrderDishesEntity();
        orderDishesEntity.setQuantity(2);
        OrderDishesEntityPk pk= new OrderDishesEntityPk(1,1);
        orderDishesEntity.setId(pk);
        return orderDishesEntity;
    }

    public static OrderEntity createOrderEntity() {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(1);
        orderEntity.setDate(LocalDate.now());
        orderEntity.setChefId(1);
        orderEntity.setCustomerId(1);
        return orderEntity;
    }

    public static Dish createDish() {
        return new Dish(1, "Test Dish", 1, "", 25000, 1, "url", true);
    }

    public static DishDto createDishDto() {
        DishDto dishDto = new DishDto();
        dishDto.setName("BicMac");
        dishDto.setPrice("15000");
        dishDto.setDescription("Hamburguesa");
        dishDto.setCategoryId(1);
        dishDto.setUrlImage("url");
        return dishDto;
    }

    public static DishUpdateDto createDishUpdateDto() {
        return new DishUpdateDto(1, "20000", "Description update");
    }

    public static DishEntity createDishEntity() {
        return new DishEntity();
    }

    public static Restaurant createRestaurant() {
        return new Restaurant(1, "Test", "Test", 1, "123445", "url", "1234");
    }

    public static RestaurantDto createRestaurantDto() {
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setOwnerId(1);
        return restaurantDto;
    }

    public static RestaurantEntity createResponseEntity() {
        return new RestaurantEntity();
    }

    public static User createUser() {
        return new User(1, "John", "Doe", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", null);
    }

    public static UserDto createUserDto() {
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setLastName("Doe");
        return userDto;
    }
}
