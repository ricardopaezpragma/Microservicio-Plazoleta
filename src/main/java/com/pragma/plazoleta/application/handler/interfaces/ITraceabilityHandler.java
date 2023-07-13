package com.pragma.plazoleta.application.handler.interfaces;

import com.pragma.plazoleta.application.dto.TraceabilityDto;
import com.pragma.plazoleta.application.dto.TraceabilityRestaurantDto;


import java.util.List;

public interface ITraceabilityHandler {
    List<TraceabilityDto> getTraceabilityByCustomerId(String email);
    List<TraceabilityRestaurantDto> getAllTraceability(String email);
}
