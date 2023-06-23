package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DishUseCaseTest {
    private DishUseCase dishUseCase;

    @Mock
    private IDishPersistencePort dishPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishUseCase = new DishUseCase(dishPersistencePort);
    }

    @Test
    void testCreateDish() {
        // Mocking
        Dish dish = new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true);

        // Test
        dishUseCase.createDish(dish);

        // Verification
        verify(dishPersistencePort).createDish(dish);
    }

    @Test
    void testUpdateDish() {
        // Mocking
        Dish dish = new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true);

        // Test
        dishUseCase.updateDish(dish);

        // Verification
        verify(dishPersistencePort).updateDish(dish);
    }

    @Test
    void testGetDishById() {
        // Mocking
        int dishId = 1;
        Dish expectedDish = new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true);

        when(dishPersistencePort.getDishById(dishId)).thenReturn(expectedDish);

        // Test
        Dish actualDish = dishUseCase.getDishById(dishId);

        // Verification
        Assertions.assertEquals(expectedDish, actualDish);
    }
}


