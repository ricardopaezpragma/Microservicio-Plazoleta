package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Employee;

public interface IEmployeePersistencePort {
    void saveEmployee(Employee employee);
    Employee getEmployeeByUserId(int userId);

}
