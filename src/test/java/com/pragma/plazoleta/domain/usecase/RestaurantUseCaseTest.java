package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantUseCaseTest {
    private RestaurantUseCase restaurantUseCase;

    @Mock
    private IRestaurantPersistencePort restaurantPersistencePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantUseCase = new RestaurantUseCase(restaurantPersistencePort);
    }
    @Test
     void testGetAllRestaurants() {
        int page = 0;
        int size = 10;
        Page<Restaurant> expectedPage = mock(Page.class);

        when(restaurantPersistencePort.getAllRestaurants(page, size)).thenReturn(expectedPage);

        Page<Restaurant> result = restaurantUseCase.getAllRestaurants(page, size);

        assertEquals(expectedPage, result);
        verify(restaurantPersistencePort, times(1)).getAllRestaurants(page, size);
    }

    @Test
    void testGetRestaurantById() {
        // Mocking
        int restaurantId = 1;
        Restaurant expectedRestaurant = new Restaurant(1,"Test","Test",1,"123445","url","1234");
        expectedRestaurant.setId(restaurantId);
        expectedRestaurant.setName("Test Restaurant");

        when(restaurantPersistencePort.getRestaurantById(restaurantId)).thenReturn(expectedRestaurant);

        // Test
        Restaurant actualRestaurant = restaurantUseCase.getRestaurantById(restaurantId);

        // Verification
        assertEquals(expectedRestaurant, actualRestaurant);
    }

    @Test
    void testCreateRestaurant() {
        // Mocking
        Restaurant restaurant = new Restaurant(1,"Test","Test",1,"123445","url","1234");
        restaurant.setName("Test Restaurant");

        // Test
        restaurantUseCase.createRestaurant(restaurant);

        // Verification
        verify(restaurantPersistencePort).createRestaurant(restaurant);
    }
}




