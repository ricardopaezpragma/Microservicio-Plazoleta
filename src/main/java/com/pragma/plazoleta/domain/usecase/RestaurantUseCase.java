package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import org.springframework.data.domain.Page;


public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Page<Restaurant> getAllRestaurants(int page, int size) {
        return restaurantPersistencePort.getAllRestaurants(page,size);
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        return restaurantPersistencePort.getRestaurantById(restaurantId);
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.createRestaurant(restaurant);
    }

    @Override
    public Restaurant getRestaurantByOwnerId(int ownerId) {
        return restaurantPersistencePort.getRestaurantByOwnerId(ownerId);
    }
}
