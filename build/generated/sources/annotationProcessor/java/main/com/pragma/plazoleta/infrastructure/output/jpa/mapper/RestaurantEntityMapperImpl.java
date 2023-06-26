package com.pragma.plazoleta.infrastructure.output.jpa.mapper;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-25T18:11:01-0500",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class RestaurantEntityMapperImpl implements RestaurantEntityMapper {

    @Override
    public RestaurantEntity toEntity(Restaurant restaurant) {
        if ( restaurant == null ) {
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setId( restaurant.getId() );
        restaurantEntity.setName( restaurant.getName() );
        restaurantEntity.setAddress( restaurant.getAddress() );
        restaurantEntity.setOwnerId( restaurant.getOwnerId() );
        restaurantEntity.setPhone( restaurant.getPhone() );
        restaurantEntity.setUrlLogo( restaurant.getUrlLogo() );
        restaurantEntity.setNit( restaurant.getNit() );

        return restaurantEntity;
    }

    @Override
    public Restaurant toRestaurant(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        Integer id = null;
        String name = null;
        String address = null;
        Integer ownerId = null;
        String phone = null;
        String urlLogo = null;
        String nit = null;

        id = restaurantEntity.getId();
        name = restaurantEntity.getName();
        address = restaurantEntity.getAddress();
        ownerId = restaurantEntity.getOwnerId();
        phone = restaurantEntity.getPhone();
        urlLogo = restaurantEntity.getUrlLogo();
        nit = restaurantEntity.getNit();

        Restaurant restaurant = new Restaurant( id, name, address, ownerId, phone, urlLogo, nit );

        return restaurant;
    }
}
