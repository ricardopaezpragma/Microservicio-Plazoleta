package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import org.springframework.data.domain.Page;


public interface IDishHandler {
    void createDish(DishDto dishDto);
    void updateDish(DishUpdateDto dishUpdateDto);
    DishDto getDishById(int dishId);
    void toggleDishStatus(String email, int dishId);
    Page<DishDto> getDishesByRestaurantIdAndCategoryId(int restaurantId, int categoryId, int page, int size);


}
