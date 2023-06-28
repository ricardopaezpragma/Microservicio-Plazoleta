package com.pragma.plazoleta.infrastructure.output.jpa.mapper;

import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IDishRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class OrderEntityMapper {
    @Autowired
    private IRestaurantRepository restaurantRepository;
    @Autowired
    private IDishRepository dishRepository;

    @Mapping(target = "restaurant", expression = "java(getRestaurant(order.getRestaurantId()))")
    public abstract OrderEntity toEntity(Order order);

    @Mapping(target = "restaurantId", source = "restaurant.id")
    public abstract Order toDomain(OrderEntity orderEntity);

    public abstract List<Order> toDomainList(List<OrderEntity> orderEntities);


    protected RestaurantEntity getRestaurant(int restaurantId) {
        return restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
    }

}
