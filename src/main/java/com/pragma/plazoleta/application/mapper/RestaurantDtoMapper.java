package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RestaurantDtoMapper {
    Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);

    RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
}
