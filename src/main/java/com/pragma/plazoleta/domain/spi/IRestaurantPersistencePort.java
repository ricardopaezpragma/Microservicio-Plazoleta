package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Restaurant;

public interface IRestaurantPersistencePort {
    void crateRestaurant(Restaurant restaurant);
}
