package com.pragma.plazoleta.application.handler.interfaces;

import com.pragma.plazoleta.application.dto.TraceabilityDto;


import java.util.List;

public interface ITraceabilityHandler {
    List<TraceabilityDto> getTraceabilityByCustomerId(String email);
}
