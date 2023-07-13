package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import static org.junit.jupiter.api.Assertions.*;

import com.pragma.plazoleta.domain.model.Employee;
import com.pragma.plazoleta.infrastructure.exception.NotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.EmployeeEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IEmployeeRepository;
import com.pragma.plazoleta.manager.ManagerFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class EmployeeAdapterTest {

    private EmployeeAdapter employeeAdapter;

    @Mock
    private IEmployeeRepository employeeRepository;

    @Mock
    private EmployeeEntityMapper employeeEntityMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        employeeAdapter = new EmployeeAdapter(employeeRepository, employeeEntityMapper);
    }

    @Test
    void saveEmployee_shouldCallEmployeeRepositorySave() {
        // Arrange
        Employee employee =  ManagerFactory.createEmployee();
        EmployeeEntity employeeEntity = ManagerFactory.createEmployeeEntity();
        when(employeeEntityMapper.toEntity(employee)).thenReturn(employeeEntity);

        // Act
        employeeAdapter.saveEmployee(employee);

        // Assert
        verify(employeeRepository, times(1)).save(employeeEntity);
    }

    @Test
    void getEmployeeByUserId_existingUser_shouldReturnMappedEmployee() {
        // Arrange
        int userId = 1;
        EmployeeEntity employeeEntity = ManagerFactory.createEmployeeEntity();
        Employee employee = ManagerFactory.createEmployee();
        when(employeeRepository.findByUserId(userId)).thenReturn(Optional.of(employeeEntity));
        when(employeeEntityMapper.toDomain(employeeEntity)).thenReturn(employee);

        // Act
        Employee result = employeeAdapter.getEmployeeByUserId(userId);

        // Assert
        assertEquals(employee, result);
    }

    @Test
    void getEmployeeByUserId_nonExistingUser_shouldThrowNotFoundException() {
        // Arrange
        int userId = 123;
        when(employeeRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NotFoundException.class, () -> employeeAdapter.getEmployeeByUserId(userId));
    }
}
