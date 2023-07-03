package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.IEmployeeServicePort;
import com.pragma.plazoleta.domain.model.Employee;
import com.pragma.plazoleta.domain.spi.IEmployeePersistencePort;

public class EmployeeUseCase implements IEmployeeServicePort {
    private final IEmployeePersistencePort employeePersistencePort;

    public EmployeeUseCase(IEmployeePersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
    }

    @Override
    public void saveEmployee(Employee employee) {
        employeePersistencePort.saveEmployee(employee);
    }

    @Override
    public Employee getEmployeeByUserId(int userId) {
        return employeePersistencePort.getEmployeeByUserId(userId);
    }
}
