package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    Restaurant getRestaurantById(int restaurantId);
    void createRestaurant(Restaurant restaurant);
}
