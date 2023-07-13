package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.DishEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IDishRepository;
import com.pragma.plazoleta.manager.ManagerFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.mockito.Mockito.*;

class DishJpaAdapterTest {
    private DishJpaAdapter dishJpaAdapter;

    @Mock
    private IDishRepository dishRepository;

    @Mock
    private DishEntityMapper dishEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishJpaAdapter = new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Test
    void testCreateDish() {
        // Mocking
        Dish dish = ManagerFactory.createDish();
        DishEntity dishEntity = ManagerFactory.createDishEntity();

        when(dishEntityMapper.toEntity(dish)).thenReturn(dishEntity);

        // Test
        dishJpaAdapter.createDish(dish);

        // Verification
        verify(dishRepository, times(1)).save(dishEntity);
    }

    @Test
    void testUpdateDish() {
        // Mocking
        Dish dish = ManagerFactory.createDish();
        DishEntity dishEntity = ManagerFactory.createDishEntity();

        when(dishEntityMapper.toEntity(dish)).thenReturn(dishEntity);

        // Test
        dishJpaAdapter.updateDish(dish);

        // Verification
        verify(dishRepository, times(1)).save(dishEntity);
    }

    @Test
    void testGetDishById() {
        // Mocking
        int dishId = 1;
        Dish expectedDish = ManagerFactory.createDish();
        DishEntity dishEntity = ManagerFactory.createDishEntity();

        when(dishRepository.findById(dishId)).thenReturn(Optional.of(dishEntity));
        when(dishEntityMapper.toDish(dishEntity)).thenReturn(expectedDish);

        // Test
        Dish actualDish = dishJpaAdapter.getDishById(dishId);

        // Verification
        verify(dishRepository, times(1)).findById(dishId);
        verify(dishEntityMapper, times(1)).toDish(dishEntity);
        Assertions.assertEquals(expectedDish, actualDish);
    }

    @Test
    void testGetDishesByRestaurantIdAndCategoryId() {
        int restaurantId = 1;
        int categoryId = 2;
        int page = 0;
        int size = 10;
        Page<DishEntity> dishEntityPage = mock(Page.class);
        Page<Dish> expectedPage = mock(Page.class);

        when(dishRepository.findAllByRestaurantIdAndCategoryIdAndIsActive(
                restaurantId, categoryId, true, PageRequest.of(page, size, Sort.by("name"))))
                .thenReturn(dishEntityPage);
        when(dishEntityMapper.toDishPage(dishEntityPage)).thenReturn(expectedPage);

        Page<Dish> result = dishJpaAdapter.getDishesByRestaurantIdAndCategoryId(restaurantId, categoryId, page, size);

        Assertions.assertEquals(expectedPage, result);
        verify(dishRepository, times(1)).findAllByRestaurantIdAndCategoryIdAndIsActive(
                restaurantId, categoryId, true, PageRequest.of(page, size, Sort.by("name")));
        verify(dishEntityMapper, times(1)).toDishPage(dishEntityPage);
    }
}

