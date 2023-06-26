package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Dish;
import org.springframework.data.domain.Page;

public interface IDishServicePort {
    void createDish(Dish dish);
    void updateDish(Dish dish);
    Dish getDishById(int dishId);
    Page<Dish> getDishesByRestaurantIdAndCategoryId(int restaurantId,int categoryId,int page,int size);

}
