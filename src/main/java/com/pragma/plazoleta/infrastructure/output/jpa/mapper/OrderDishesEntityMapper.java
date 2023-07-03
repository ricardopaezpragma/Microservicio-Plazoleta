package com.pragma.plazoleta.infrastructure.output.jpa.mapper;

import com.pragma.plazoleta.domain.model.OrderDishes;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderDishesEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderDishesEntityMapper {
    @Mapping(target = "id.dishId", source = "dishId")
    OrderDishesEntity toEntity(OrderDishes orderDishes);
    @Mapping(source = "id.dishId", target = "dishId")
    OrderDishes toDomain(OrderDishesEntity orderDishesEntity);

    default List<OrderDishesEntity> toEntityList(List<OrderDishes> orderDishesList) {
        return orderDishesList.stream()
                .map(this::toEntity)
                .toList();
    }

    default List<OrderDishes> toDomainList(List<OrderDishesEntity> orderDishesEntities) {
        return orderDishesEntities.stream()
                .map(this::toDomain)
                .toList();
    }
}
