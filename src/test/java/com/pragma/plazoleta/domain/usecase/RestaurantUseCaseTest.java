package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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




