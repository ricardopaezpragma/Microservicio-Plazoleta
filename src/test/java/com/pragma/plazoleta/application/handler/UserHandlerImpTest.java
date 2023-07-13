package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.implementations.UserHandlerImp;
import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.Employee;
import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.manager.ManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserHandlerImpTest {

    private UserHandlerImp userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private UserDtoMapper userDtoMapper;

    @Mock
    private IEmployeeServicePort employeeServicePort;

    @Mock
    private IRestaurantServicePort restaurantServicePort;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        userHandler = new UserHandlerImp(userServicePort, userDtoMapper, employeeServicePort, restaurantServicePort);
    }

    @Test
    void saveOwner_shouldSaveUserWithRolePropietario() {
        // Arrange
        UserDto userDto = ManagerFactory.createUserDto();
        User user = ManagerFactory.createUser();
        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        // Act
        userHandler.saveOwner(userDto);

        // Assert
        verify(userServicePort, times(1)).saveUser(user);
        assertEquals("PROPIETARIO", user.getRole());
    }

    @Test
    void saveEmployee_shouldSaveUserWithRoleEmpleadoAndSaveEmployee() {
        // Arrange
        UserDto userDto = new UserDto();
        String email = "test@example.com";
        User user = ManagerFactory.createUser();
        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);
        when(userServicePort.saveUser(user)).thenReturn(ManagerFactory.createUser());
        when(userServicePort.getUserIdByEmail(email)).thenReturn(1);
        when(restaurantServicePort.getRestaurantByOwnerId(1)).thenReturn(ManagerFactory.createRestaurant());

        // Act
        userHandler.saveEmployee(userDto, email);

        // Assert
        verify(userServicePort, times(1)).saveUser(user);
        assertEquals("EMPLEADO", user.getRole());
        verify(employeeServicePort, times(1)).saveEmployee(any(Employee.class));
    }

    @Test
    void saveCustomer_shouldSaveUserWithRoleCliente() {
        // Arrange
        UserDto userDto = ManagerFactory.createUserDto();
        User user = ManagerFactory.createUser();
        when(userDtoMapper.userDtoToUser(userDto)).thenReturn(user);

        // Act
        userHandler.saveCustomer(userDto);

        // Assert
        verify(userServicePort, times(1)).saveUser(user);
        assertEquals("CLIENTE", user.getRole());
    }
}