package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;


public interface IDishHandler {
    void createDish(DishDto dishDto);
    void updateDish(DishUpdateDto dishUpdateDto);
    DishDto getDishById(int dishId);
    void toggleDishStatus(String email, int dishId);

}
