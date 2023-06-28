package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import org.springframework.data.domain.Page;


public interface IDishHandler {
    void createDish(String email,DishDto dishDto);
    void updateDish(String email,DishUpdateDto dishUpdateDto);
    DishDto getDishById(int dishId);
    void toggleDishStatus(String email, int dishId);
    Page<DishDto> getDishesByRestaurantIdAndCategoryId(int restaurantId, int categoryId, int page, int size);


}
