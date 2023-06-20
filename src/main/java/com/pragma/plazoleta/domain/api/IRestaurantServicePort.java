package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Restaurant;

public interface IRestaurantServicePort {
    Restaurant getRestaurantById(int restaurantId);
    void crateRestaurant(Restaurant restaurant);
}
