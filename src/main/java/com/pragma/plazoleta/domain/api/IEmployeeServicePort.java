package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Employee;

public interface IEmployeeServicePort {
    void saveEmployee(Employee employee);
    Employee getEmployeeByUserId(int userId);
}
