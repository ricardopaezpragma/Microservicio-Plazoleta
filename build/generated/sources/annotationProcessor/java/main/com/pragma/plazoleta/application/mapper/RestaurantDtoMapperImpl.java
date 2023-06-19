package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.domain.model.Restaurant;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-19T00:36:55-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class RestaurantDtoMapperImpl implements RestaurantDtoMapper {

    @Override
    public Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto) {
        if ( restaurantDto == null ) {
            return null;
        }

        String name = null;
        String address = null;
        Integer ownerId = null;
        String phone = null;
        String urlLogo = null;
        String nit = null;

        name = restaurantDto.getName();
        address = restaurantDto.getAddress();
        ownerId = restaurantDto.getOwnerId();
        phone = restaurantDto.getPhone();
        urlLogo = restaurantDto.getUrlLogo();
        nit = restaurantDto.getNit();

        Integer id = null;

        Restaurant restaurant = new Restaurant( id, name, address, ownerId, phone, urlLogo, nit );

        return restaurant;
    }

    @Override
    public RestaurantDto restaurantToRestaurantDto(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantDto restaurantDto = new RestaurantDto();

        restaurantDto.setName( restaurant.getName() );
        restaurantDto.setAddress( restaurant.getAddress() );
        restaurantDto.setOwnerId( restaurant.getOwnerId() );
        restaurantDto.setPhone( restaurant.getPhone() );
        restaurantDto.setUrlLogo( restaurant.getUrlLogo() );
        restaurantDto.setNit( restaurant.getNit() );

        return restaurantDto;
    }
}
