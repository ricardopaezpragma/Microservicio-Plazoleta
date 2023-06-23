package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.DishEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IDishRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
        Dish dish = new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true);
        DishEntity dishEntity = new DishEntity();

        when(dishEntityMapper.toEntity(dish)).thenReturn(dishEntity);

        // Test
        dishJpaAdapter.createDish(dish);

        // Verification
        verify(dishRepository, times(1)).save(dishEntity);
    }

    @Test
    void testUpdateDish() {
        // Mocking
        Dish dish = new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true);
        DishEntity dishEntity = new DishEntity();

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
        DishEntity dishEntity = new DishEntity();
        Dish expectedDish = new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true);

        when(dishRepository.findById(dishId)).thenReturn(Optional.of(dishEntity));
        when(dishEntityMapper.toDish(dishEntity)).thenReturn(expectedDish);

        // Test
        Dish actualDish = dishJpaAdapter.getDishById(dishId);

        // Verification
        verify(dishRepository, times(1)).findById(dishId);
        verify(dishEntityMapper, times(1)).toDish(dishEntity);
        Assertions.assertEquals(expectedDish, actualDish);
    }
}
