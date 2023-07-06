package com.pragma.plazoleta.application.handler.interfaces;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.dto.RestaurantResponse;
import org.springframework.data.domain.Page;


public interface IRestaurantHandler {
    void createRestaurant(RestaurantDto restaurantDto);
    Page<RestaurantResponse> getAllRestaurants(int page, int size);
}
