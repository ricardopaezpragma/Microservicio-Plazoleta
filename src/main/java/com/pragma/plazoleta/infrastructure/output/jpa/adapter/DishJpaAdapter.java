package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IDishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class DishJpaAdapter implements IDishPersistencePort {

    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;

    @Override
    public void createDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public void updateDish(Dish dish) {
        dishRepository.save(dishEntityMapper.toEntity(dish));
    }

    @Override
    public Dish getDishById(int dishId) {
        return dishEntityMapper.toDish(dishRepository.findById(dishId)
                .orElseThrow(() -> new NotFoundException("I don't know found the dish with id: " +dishId)));
    }

    @Override
    public Page<Dish> getDishesByRestaurantIdAndCategoryId(int restaurantId, int categoryId, int page, int size) {
        return dishEntityMapper.toDishPage(
                dishRepository.findAllByRestaurantIdAndCategoryIdAndIsActive(
                        restaurantId, categoryId,true, PageRequest.of(page,size, Sort.by("name"))));
    }

}
