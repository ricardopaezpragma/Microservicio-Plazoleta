package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import org.springframework.data.domain.Page;

public class DishUseCase implements IDishServicePort {
    private final IDishPersistencePort dishPersistencePort;

    public DishUseCase(IDishPersistencePort dishPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
    }

    @Override
    public void createDish(Dish dish) {
        dishPersistencePort.createDish(dish);
    }

    @Override
    public void updateDish(Dish dish) {
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public Dish getDishById(int dishId) {
        return dishPersistencePort.getDishById(dishId);
    }

    @Override
    public Page<Dish> getDishesByRestaurantIdAndCategoryId(int restaurantId, int categoryId, int page, int size) {
        return dishPersistencePort.getDishesByRestaurantIdAndCategoryId(restaurantId, categoryId, page, size);
    }
}
