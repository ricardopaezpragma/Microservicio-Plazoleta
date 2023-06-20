package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Dish;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(Dish dish);
    Dish getDishById(int dishId);

}
