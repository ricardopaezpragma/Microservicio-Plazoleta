package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.mapper.DishDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class DishHandlerImp implements IDishHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishServicePort dishServicePort;
    private final DishDtoMapper dishDtoMapper;

    @Override
    public void createDish(DishDto dishDto) {
        dishDto.setActive(true);
        restaurantServicePort.getRestaurantById(dishDto.getRestaurantId());
        dishServicePort.createDish(dishDtoMapper.dishDtoToDish(dishDto));
    }
}
