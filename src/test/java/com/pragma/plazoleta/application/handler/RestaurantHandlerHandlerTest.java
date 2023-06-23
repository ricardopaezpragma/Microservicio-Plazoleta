package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.exception.UserIsNotOwnerException;
import com.pragma.plazoleta.application.mapper.RestaurantDtoMapper;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


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
        User user = new User(1,"John","Doe","11234","31203948", LocalDate.of(1955,01,01),"email@emal.com","password","Propietario");

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
        User user = new User(1,"Smith","Jane","11234","31203948", LocalDate.of(1955,01,01),"email@emal.com","password","Empleado");

        when(userServicePort.getUserById(restaurantDto.getOwnerId())).thenReturn(user);

        // Test and Verification
        assertThrows(UserIsNotOwnerException.class, () -> restaurantHandler.createRestaurant(restaurantDto));
    }


}