package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;


public interface IRestaurantServicePort {
    Page<Restaurant> getAllRestaurants(int page, int size);
    Restaurant getRestaurantById(int restaurantId);
    void createRestaurant(Restaurant restaurant);
}
