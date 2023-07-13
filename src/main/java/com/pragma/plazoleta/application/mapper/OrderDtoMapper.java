package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.OrderRequestDto;
import com.pragma.plazoleta.application.dto.OrderResponseDto;
import com.pragma.plazoleta.domain.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDtoMapper {
    Order toModel(OrderRequestDto orderRequestDto);
    OrderResponseDto toResponse(Order order);
    default Page<OrderResponseDto> toResponsePage(Page<Order> orderPage){
        return orderPage.map(this::toResponse);
    }

}
