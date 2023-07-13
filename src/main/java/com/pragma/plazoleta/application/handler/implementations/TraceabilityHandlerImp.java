package com.pragma.plazoleta.application.handler.implementations;

import com.pragma.plazoleta.application.dto.TraceabilityDto;
import com.pragma.plazoleta.application.dto.TraceabilityRestaurantDto;
import com.pragma.plazoleta.application.handler.interfaces.ITraceabilityHandler;
import com.pragma.plazoleta.application.mapper.TraceabilityDtoMapper;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.ITraceabilityServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.enums.OrderStatus;
import com.pragma.plazoleta.domain.model.Order;
import com.pragma.plazoleta.domain.model.Traceability;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TraceabilityHandlerImp implements ITraceabilityHandler {
    private final ITraceabilityServicePort traceabilityServicePort;
    private final TraceabilityDtoMapper traceabilityDtoMapper;
    private final IUserServicePort userServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderServicePort orderServicePort;

    @Override
    public List<TraceabilityDto> getTraceabilityByCustomerId(String email) {
        int customerId = userServicePort.getUserIdByEmail(email);
        return traceabilityServicePort.getTraceabilityByCustomerId(customerId)
                .stream()
                .map(this.traceabilityDtoMapper::toDto)
                .toList();
    }

    @Override
    public List<TraceabilityRestaurantDto> getAllTraceability(String email) {

        int ownerId = userServicePort.getUserIdByEmail(email);
        int restaurantId = restaurantServicePort.getRestaurantByOwnerId(ownerId).getId();
        return orderServicePort.getOrdersByRestaurantId(restaurantId)
                .stream()
                .map(Order::getId)
                .map(this.traceabilityServicePort::getTraceabilityByOrderId)
                .map(traceability ->
                        traceability.stream()
                                .collect(Collectors.groupingBy(Traceability::getOrderId))
                                .values()
                                .stream()
                                .filter(traceabilityList -> traceabilityList.stream().anyMatch(order -> order.getNewState() == OrderStatus.ENTREGADO))
                                .map(traceabilityList ->
                                        traceabilityList.stream()
                                                .filter(t -> t.getNewState() == OrderStatus.EN_PREPARACION || t.getNewState() == OrderStatus.ENTREGADO)
                                                .collect(Collectors.toMap(Traceability::getOrderId,
                                                        t -> t.getDate().getHour() + ":" + t.getDate().getMinute(),
                                                        (oldValue, newValue) -> oldValue + "-" + newValue))
                                )
                                .collect(Collectors.toMap(Map::keySet, Map::values))

                )
                .filter(setSetMap -> setSetMap.size() > 0)
                .map(setCollectionMap -> {
                    Integer orderId = setCollectionMap.keySet().stream().findFirst().get().stream().findFirst().get();
                    var time = setCollectionMap.values().toString().replace("[", "").replace("]", "").split("-");
                    Duration duration = Duration.between(LocalTime.parse(time[0]), LocalTime.parse(time[1]));
                    return new TraceabilityRestaurantDto(orderId, duration.toMinutes() + " Min : " + duration.getSeconds() + " Sec");
                })
                .toList();
    }

}
