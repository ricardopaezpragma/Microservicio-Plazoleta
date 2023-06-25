package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.dto.RestaurantResponse;
import com.pragma.plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;



@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantDtoMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);

    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
    RestaurantResponse restaurantToRestaurantResponse(Restaurant restaurant);
    default Page<RestaurantResponse> toRestaurantResponsePage(Page<Restaurant> restaurants) {
        return restaurants.map(this::restaurantToRestaurantResponse);
    }
}
