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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserHandlerImpTest {
    private UserHandlerImp userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserDtoMapper userDtoMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userHandler = new UserHandlerImp(userServicePort, userDtoMapper);
    }

    @Test
    void testGetUserByEmail() {
        // Mocking
        String email = "test@example.com";
        User user = new User("John","Doe","11234","31203948", LocalDate.of(1955,01,01),"email@emal.com","password","NULL");
        user.setName("Test User");
        UserDto expectedUserDto = new UserDto();
        expectedUserDto.setName("Test User DTO");

        when(userServicePort.getUserByEmail(email)).thenReturn(user);
        when(userDtoMapper.userToUserDto(user)).thenReturn(expectedUserDto);

        // Test
        UserDto actualUserDto = userHandler.getUserByEmail(email);

        // Verification
        assertEquals(expectedUserDto, actualUserDto);
    }

    @Test
    void testSaveOwner() {
        // Mocking
        UserDto userDto = new UserDto();

        // Test
        userHandler.saveOwner(userDto);

        // Verification
        verify(userDtoMapper).userDtoToUser(userDto);
        assertEquals("Propietario", userDto.getRole());
        verify(userServicePort).saveUser(userDtoMapper.userDtoToUser(userDto));
    }

    @Test
    void testSaveEmployee() {
        // Mocking
        UserDto userDto = new UserDto();

        // Test
        userHandler.saveEmployee(userDto);

        // Verification
        verify(userDtoMapper).userDtoToUser(userDto);
        assertEquals("Empleado", userDto.getRole());
        verify(userServicePort).saveUser(userDtoMapper.userDtoToUser(userDto));
    }

    @Test
    void testSaveUser() {
        // Mocking
        UserDto userDto = new UserDto();

        // Test
        userHandler.saveUser(userDto);

        // Verification
        verify(userDtoMapper).userDtoToUser(userDto);
        verify(userServicePort).saveUser(userDtoMapper.userDtoToUser(userDto));
    }
}

