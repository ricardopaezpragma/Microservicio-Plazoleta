package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class UserHandlerImpTest {
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
    public void testSaveOwner() {
        UserDto userDto = new UserDto();

        User user = new User(1, "John", "Doe", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "NULL");
        user.setRole("Propietario");

        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        userHandler.saveOwner(userDto);

        verify(userDtoMapper, times(1)).userDtoToUser(userDto);
        verify(userServicePort, times(1)).saveUser(user);
    }

    @Test
    public void testSaveEmployee() {
        UserDto userDto = new UserDto();

        User user = new User(1, "John", "Doe", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "NULL");
        user.setRole("Empleado");

        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        userHandler.saveEmployee(userDto);

        verify(userDtoMapper, times(1)).userDtoToUser(userDto);
        verify(userServicePort, times(1)).saveUser(user);
    }

    @Test
    public void testSaveUser() {
        User user = new User(1, "John", "Doe", "11234", "31203948", LocalDate.of(1955, 01, 01), "email@emal.com", "password", "NULL");

        userHandler.saveUser(user);

        verify(userServicePort, times(1)).saveUser(user);
    }
}