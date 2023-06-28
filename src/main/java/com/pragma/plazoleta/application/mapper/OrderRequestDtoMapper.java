package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderRequestDtoMapper {
    Order toModel(OrderRequestDto orderRequestDto);
    OrderRequestDto toDto(Order order);
}
