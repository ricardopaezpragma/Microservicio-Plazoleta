package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Restaurant;
import org.springframework.data.domain.Page;

public interface IRestaurantPersistencePort {
    Page<Restaurant> getAllRestaurants(int page, int size);
    Restaurant getRestaurantById(int restaurantId);
    Restaurant getRestaurantByOwnerId(int ownerId);
    void createRestaurant(Restaurant restaurant);
}
