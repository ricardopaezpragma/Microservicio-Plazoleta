package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import com.pragma.plazoleta.application.exception.UnauthorizedDishModificationException;
import com.pragma.plazoleta.application.mapper.DishDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Transactional
@Service
@RequiredArgsConstructor
public class DishHandlerImp implements IDishHandler {
    private final IRestaurantServicePort restaurantServicePort;
    private final IDishServicePort dishServicePort;
    private final IUserServicePort userServicePort;
    private final DishDtoMapper dishDtoMapper;

    @Override
    public void createDish(DishDto dishDto) {
        dishDto.setActive(true);
        restaurantServicePort.getRestaurantById(dishDto.getRestaurantId());
        dishServicePort.createDish(dishDtoMapper.dishDtoToDish(dishDto));
    }

    @Override
    public void updateDish(DishUpdateDto dishUpdateDto) {
        Dish dish = dishDtoMapper.dishDtoToDish(this.getDishById(dishUpdateDto.id()));
        dish.setId(dishUpdateDto.id());
        dish.setPrice(Integer.parseInt(dishUpdateDto.price()));
        dish.setDescription(dishUpdateDto.description());
        dishServicePort.updateDish(dish);
    }

    @Override
    public DishDto getDishById(int dishId) {
        return dishDtoMapper.dishToDishDto(dishServicePort.getDishById(dishId));
    }

    @Override
    public void toggleDishStatus(String email, int dishId) {
        var dish= dishServicePort.getDishById(dishId);
        int ownerId = restaurantServicePort.getRestaurantById(dish.getRestaurantId()).getOwnerId();
        int userId =userServicePort.getUserIdByEmail(email);
        if(ownerId!=userId){
            throw new UnauthorizedDishModificationException(userId,dishId);
        }
        dish.setActive(!dish.isActive());
        dishServicePort.updateDish(dish);
    }

    @Override
    public Page<DishDto> getDishesByRestaurantIdAndCategoryId(int restaurantId, int categoryId, int page, int size) {
        return dishDtoMapper.toDishDtoPage(
                dishServicePort.getDishesByRestaurantIdAndCategoryId(restaurantId,categoryId,page,size));
    }

}
