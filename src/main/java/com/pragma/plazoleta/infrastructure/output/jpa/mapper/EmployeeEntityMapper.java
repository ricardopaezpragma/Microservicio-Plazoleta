package com.pragma.plazoleta.infrastructure.output.jpa.mapper;

import com.pragma.plazoleta.domain.model.Employee;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmployeeEntityMapper {
    @Mapping(source = "id.restaurantId", target = "restaurantId")
    @Mapping(source = "id.userId", target = "userId")
    Employee toDomain(EmployeeEntity employeeEntity);
    @Mapping(target = "id.restaurantId", source = "restaurantId")
    @Mapping(target = "id.userId", source = "userId")
    EmployeeEntity toEntity(Employee employee);
}
