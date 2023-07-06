package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.implementations.UserHandlerImp;
import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserHandlerImpTest {

    private IUserServicePort userServicePort;
    private UserDtoMapper userDtoMapper;
    private UserHandlerImp userHandler;

    @BeforeEach
    public void setUp() {
        userServicePort = mock(IUserServicePort.class);
        userDtoMapper = mock(UserDtoMapper.class);
        userHandler = new UserHandlerImp(userServicePort, userDtoMapper);
    }

    @Test
    void testSaveOwner() {
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setLastName("Doe");

        User user = new User(1, "Test", "Test", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "PROPIETARIO");

        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        userHandler.saveOwner(userDto);

        verify(userDtoMapper, times(1)).userDtoToUser(userDto);
        verify(userServicePort, times(1)).saveUser(user);
    }

    @Test
    void testSaveEmployee() {
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setLastName("Doe");

        User user = new User(1, "Test", "Test", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "EMPLEADO");

        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        //userHandler.saveEmployee(userDto);

        verify(userDtoMapper, times(1)).userDtoToUser(userDto);
        verify(userServicePort, times(1)).saveUser(user);
    }
    @Test
    void testSaveCustomer() {
        UserDto userDto = new UserDto();
        userDto.setName("John");
        userDto.setLastName("Doe");

        User user = new User(1, "Test", "Test", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "CLIENTE");

        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        userHandler.saveEmployee(userDto);

        verify(userDtoMapper, times(1)).userDtoToUser(userDto);
        verify(userServicePort, times(1)).saveUser(user);
    }
}
