package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.domain.model.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DishDtoMapper {
    Dish dishDtoToDish(DishDto dishDto);
    DishDto dishToDishDto(Dish dish);
   default Page<DishDto> toDishDtoPage(Page<Dish> dishPage){
       return dishPage.map(this::dishToDishDto);
   }
}
