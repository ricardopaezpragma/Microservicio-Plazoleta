package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;

public class RestaurantUseCase implements IRestaurantServicePort {
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        return restaurantPersistencePort.getRestaurantById(restaurantId);
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantPersistencePort.createRestaurant(restaurant);
    }
}
