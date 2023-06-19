package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.mapper.RestaurantDtoMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantHandlerHandler implements IRestaurantHandler{
    private final IRestaurantServicePort restaurantServicePort;
    private final RestaurantDtoMapper restaurantDtoMapper;

    @Override
    public void crateRestaurant(RestaurantDto restaurantDto) {
        restaurantServicePort.crateRestaurant(restaurantDtoMapper.restaurantDtoToRestaurant(restaurantDto));
    }
}
