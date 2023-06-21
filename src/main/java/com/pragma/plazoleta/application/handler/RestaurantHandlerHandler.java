package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.exception.UserIsNotOwnerException;
import com.pragma.plazoleta.application.mapper.RestaurantDtoMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandlerHandler implements IRestaurantHandler{
    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoMapper restaurantDtoMapper;
    private final IUserServicePort userServicePort;

    @Override
    public void crateRestaurant(RestaurantDto restaurantDto) {
        User user = userServicePort.getUserById(restaurantDto.getOwnerId());
        if (!user.getRole().equals("Propietario")) {
            throw new UserIsNotOwnerException(user.getName(),user.getLastName());
        }
        restaurantServicePort.crateRestaurant(restaurantDtoMapper.restaurantDtoToRestaurant(restaurantDto));
    }
}
