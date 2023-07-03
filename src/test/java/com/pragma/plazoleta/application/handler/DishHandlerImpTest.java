package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import com.pragma.plazoleta.application.exception.UnauthorizedDishModificationException;
import com.pragma.plazoleta.application.mapper.DishDtoMapper;
import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.domain.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class DishHandlerImpTest {
    @InjectMocks
    private DishHandlerImp dishHandler;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private IDishServicePort dishServicePort;
    @Mock
    private IUserServicePort userServicePort;
    @Mock
    private DishDtoMapper dishDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dishHandler = new DishHandlerImp(restaurantServicePort, dishServicePort, userServicePort, dishDtoMapper);
    }

    @Test
    void testToggleDishStatus_WhenOwnerMatchesUserId() {
        String email = "owner@example.com";
        int dishId = 1;
        int ownerId = 1;
        int userId = 1;

        Dish dish = new Dish(dishId, "Test Dish", 2, "", 25000, 1, "url", true);

        Restaurant restaurant = new Restaurant(1, "Test", "Test", ownerId, "123445", "url", "1234");

        when(dishServicePort.getDishById(dishId)).thenReturn(dish);
        when(restaurantServicePort.getRestaurantById(dish.getRestaurantId())).thenReturn(restaurant);
        when(userServicePort.getUserIdByEmail(email)).thenReturn(userId);

        dishHandler.toggleDishStatus(email, dishId);

        assertFalse(dish.isActive());
        verify(dishServicePort, times(1)).updateDish(dish);
    }

    @Test
    void testToggleDishStatus_WhenOwnerDoesNotMatchUserId() {
        String email = "user@example.com";
        int dishId = 1;
        int ownerId = 2;
        int userId = 1;

        Dish dish = new Dish(dishId, "Test Dish", 2, "", 25000, 3, "url", true);


        Restaurant restaurant = new Restaurant(1, "Test", "Test", ownerId, "123445", "url", "1234");

        when(dishServicePort.getDishById(dishId)).thenReturn(dish);
        when(restaurantServicePort.getRestaurantById(dish.getRestaurantId())).thenReturn(restaurant);
        when(userServicePort.getUserIdByEmail(email)).thenReturn(userId);

        assertThrows(UnauthorizedDishModificationException.class, () -> {
            dishHandler.toggleDishStatus(email, dishId);
        });

    }

    @Test
    void testUpdateDish() {
        String email = "test@example.com";
        DishUpdateDto dishUpdateDto = new DishUpdateDto(1, "20000", "Description update");

        DishDto dishDto = new DishDto();
       // dishDto.setRestaurantId(1);
        dishDto.setName("BicMac");
        dishDto.setPrice("15000");
        dishDto.setDescription("Hamburguesa");
        dishDto.setCategoryId(2);
        dishDto.setUrlImage("url");

        Dish dish = new Dish(1, "BicMac", 2, "Hamburguesa", 15000, 1, "url", true);

        when(dishHandler.getDishById(dishUpdateDto.id()))
                .thenReturn(dishDto);
        when(dishDtoMapper.dishDtoToDish(dishDto)).thenReturn(dish);
        when(userServicePort.getUserIdByEmail(email)).thenReturn(1);
        when(restaurantServicePort.getRestaurantById(dish.getRestaurantId()))
                .thenReturn(new Restaurant(1, "Test", "Test", 1, "123445", "url", "1234"));
        doNothing().when(dishServicePort).updateDish(any(Dish.class));

        dishHandler.updateDish(email, dishUpdateDto);

        verify(dishDtoMapper, times(1)).dishDtoToDish(any(DishDto.class));
        verify(userServicePort, times(1)).getUserIdByEmail(email);
        verify(dishServicePort, times(1)).updateDish(any(Dish.class));
    }

    @Test
    void testCreateDish() {
        String email = "test@example.com";
        DishDto dishDto = new DishDto();
      //  dishDto.setRestaurantId(1);
        dishDto.setName("BicMac");
        dishDto.setPrice("15000");
        dishDto.setDescription("Hamburguesa");
        dishDto.setCategoryId(2);
        dishDto.setUrlImage("url");

        when(dishDtoMapper.dishDtoToDish(dishDto)).thenReturn(new Dish(1, "BicMac", 2, "Hamburguesa", 15000, 1, "url", true));
        when(userServicePort.getUserIdByEmail(email)).thenReturn(1);
     //   when(restaurantServicePort.getRestaurantById(dishDto.getRestaurantId())).thenReturn(new Restaurant(1, "Test", "Test", 1, "123445", "url", "1234"));
        doNothing().when(dishServicePort).createDish(any(Dish.class));

        dishHandler.createDish(email, dishDto);

        verify(dishDtoMapper, times(1)).dishDtoToDish(dishDto);
        verify(userServicePort, times(1)).getUserIdByEmail(email);
        verify(dishServicePort, times(1)).createDish(any(Dish.class));
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

    @Test
    void testGetDishesByRestaurantIdAndCategoryId() {
        int restaurantId = 1;
        int categoryId = 1;
        int page = 0;
        int size = 10;
        Page<Dish> dishPage = mock(Page.class);
        Page<DishDto> expectedPage = mock(Page.class);

        when(dishServicePort.getDishesByRestaurantIdAndCategoryId(restaurantId, categoryId, page, size)).thenReturn(dishPage);
        when(dishDtoMapper.toDishDtoPage(dishPage)).thenReturn(expectedPage);

        Page<DishDto> result = dishHandler.getDishesByRestaurantIdAndCategoryId(restaurantId, categoryId, page, size);

        assertEquals(expectedPage, result);
        verify(dishServicePort, times(1)).getDishesByRestaurantIdAndCategoryId(restaurantId, categoryId, page, size);
        verify(dishDtoMapper, times(1)).toDishDtoPage(dishPage);
    }
}







