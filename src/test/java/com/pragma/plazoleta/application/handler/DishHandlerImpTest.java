package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import com.pragma.plazoleta.application.mapper.DishDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class DishHandlerImpTest {
    @InjectMocks
    private DishHandlerImp dishHandler;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private IDishServicePort dishServicePort;

    @Mock
    private DishDtoMapper dishDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishHandler = new DishHandlerImp(restaurantServicePort, dishServicePort, dishDtoMapper);
    }

    @Test
    void testCreateDish() {
        // Mocking
        DishDto dishDto = new DishDto();
        dishDto.setRestaurantId(1);
        dishDto.setName("BicMac");
        dishDto.setActive(true);
        dishDto.setPrice("15000");
        dishDto.setDescription("Hamburguesa");
        dishDto.setCategoryId(2);
        dishDto.setUrlImage("url");

        // Test
        dishHandler.createDish(dishDto);

        // Verification
        verify(dishDtoMapper, times(1)).dishDtoToDish(dishDto);
        verify(restaurantServicePort, times(1)).getRestaurantById(dishDto.getRestaurantId());
    }

    @Test
    void testGetDishById() {
        // Mocking
        int dishId = 1;
        DishDto expectedDishDto = new DishDto();

        when(dishServicePort.getDishById(dishId)).thenReturn(new Dish(1, "Test Dish", 2, "", 25000, 3, "url", true));
        when(dishDtoMapper.dishToDishDto(any(Dish.class))).thenReturn(expectedDishDto);

        // Test
        DishDto actualDishDto = dishHandler.getDishById(dishId);

        // Verification
        verify(dishServicePort, times(1)).getDishById(dishId);
        verify(dishDtoMapper, times(1)).dishToDishDto(any(Dish.class));
        assertEquals(expectedDishDto, actualDishDto);
    }

}