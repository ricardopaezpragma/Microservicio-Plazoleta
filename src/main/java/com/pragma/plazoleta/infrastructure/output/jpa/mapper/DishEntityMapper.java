package com.pragma.plazoleta.infrastructure.output.jpa.mapper;

import com.pragma.plazoleta.domain.model.Dish;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.DishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishEntityMapper {
    @Mapping(source = "restaurantId", target = "restaurant.id")
    DishEntity toEntity(Dish dish);
    @Mapping(source = "restaurant.id", target = "restaurantId")
    Dish toDish(DishEntity dishEntity);

    default Page<Dish> toDishPage(Page<DishEntity> dishEntityPage){
        return dishEntityPage.map(this::toDish);
    }
}
