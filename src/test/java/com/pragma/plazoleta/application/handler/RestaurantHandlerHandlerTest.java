package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.dto.RestaurantResponse;
import com.pragma.plazoleta.application.exception.UserIsNotOwnerException;
import com.pragma.plazoleta.application.mapper.RestaurantDtoMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;


import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class RestaurantHandlerHandlerTest {
    private RestaurantHandlerHandler restaurantHandler;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @Mock
    private RestaurantDtoMapper restaurantDtoMapper;

    @Mock
    private IUserServicePort userServicePort;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantHandler = new RestaurantHandlerHandler(restaurantServicePort, restaurantDtoMapper, userServicePort);
    }

    @Test
    void testCrateRestaurant() {

        // Mocking
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setOwnerId(1);
        User user = new User(1, "John", "Doe", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "Propietario");

        when(userServicePort.getUserById(restaurantDto.getOwnerId())).thenReturn(user);

        // Test
        restaurantHandler.createRestaurant(restaurantDto);

        // Verification
        // Verify that the restaurantServicePort.crateRestaurant method was called
        verify(restaurantServicePort).createRestaurant(restaurantDtoMapper.restaurantDtoToRestaurant(restaurantDto));
    }

    @Test
    void testCrateRestaurantThrowsException() {
        // Mocking
        RestaurantDto restaurantDto = new RestaurantDto();
        restaurantDto.setOwnerId(1);
        User user = new User(1, "Smith", "Jane", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "Empleado");

        when(userServicePort.getUserById(restaurantDto.getOwnerId())).thenReturn(user);

        // Test and Verification
        assertThrows(UserIsNotOwnerException.class, () -> restaurantHandler.createRestaurant(restaurantDto));
    }

    @Test
    void testGetAllRestaurants() {
        int page = 0;
        int size = 10;
        Page<Restaurant> restaurantPage = mock(Page.class);
        Page<RestaurantResponse> expectedPage = mock(Page.class);

        when(restaurantServicePort.getAllRestaurants(page, size)).thenReturn(restaurantPage);
        when(restaurantDtoMapper.toRestaurantResponsePage(restaurantPage)).thenReturn(expectedPage);

        Page<RestaurantResponse> result = restaurantHandler.getAllRestaurants(page, size);

        assertEquals(expectedPage, result);
        verify(restaurantServicePort, times(1)).getAllRestaurants(page, size);
        verify(restaurantDtoMapper, times(1)).toRestaurantResponsePage(restaurantPage);
    }


}