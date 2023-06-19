package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.UserIsNotOwnerException;
import com.pragma.plazoleta.infrastructure.exception.UserNotFoundException;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.IUserMicroserviceFeign;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IUserMicroserviceFeign userMicroserviceFeign;

    @Override
    public void crateRestaurant(Restaurant restaurant) {
        userMicroserviceFeign.getUserId(restaurant.getOwnerId())
                .ifPresentOrElse(userDto -> {
                    if (!userDto.getRole().equals("Propietario")) {
                        throw new UserIsNotOwnerException(userDto.getName() + " " + userDto.getLastName() + " no es propietario.");
                    }
                    restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
                }, () -> {
                    throw new UserNotFoundException(restaurant.getOwnerId());
                });

    }
}
