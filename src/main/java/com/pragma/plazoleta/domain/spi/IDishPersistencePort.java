package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Dish;

public interface IDishPersistencePort {
    void createDish(Dish dish);
    void updateDish(Dish dish);
    Dish getDishById(int dishId);
}
