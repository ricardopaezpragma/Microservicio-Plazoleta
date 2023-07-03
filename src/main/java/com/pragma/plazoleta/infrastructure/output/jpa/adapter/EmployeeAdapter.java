package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Employee;
import com.pragma.plazoleta.domain.spi.IEmployeePersistencePort;
import com.pragma.plazoleta.infrastructure.exception.UserNotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.EmployeeEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeAdapter implements IEmployeePersistencePort {
    private final IEmployeeRepository employeeRepository;
    private final EmployeeEntityMapper employeeEntityMapper;

    @Override
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employeeEntityMapper.toEntity(employee));
    }

    @Override
    public Employee getEmployeeByUserId(int userId) {
        return employeeEntityMapper.toDomain(
                employeeRepository.findByUserId(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId))
        );
    }
}
