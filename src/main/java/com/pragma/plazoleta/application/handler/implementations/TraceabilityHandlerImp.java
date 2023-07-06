package com.pragma.plazoleta.application.handler.implementations;

import com.pragma.plazoleta.application.dto.TraceabilityDto;
import com.pragma.plazoleta.application.handler.interfaces.ITraceabilityHandler;
import com.pragma.plazoleta.application.mapper.TraceabilityDtoMapper;
import com.pragma.plazoleta.domain.api.ITraceabilityServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TraceabilityHandlerImp implements ITraceabilityHandler {
    private final ITraceabilityServicePort traceabilityServicePort;
    private final TraceabilityDtoMapper traceabilityDtoMapper;
    private final IUserServicePort userServicePort;
    @Override
    public List<TraceabilityDto> getTraceabilityByCustomerId(String email) {
        int customerId=userServicePort.getUserIdByEmail(email);
        return traceabilityServicePort.getTraceabilityByCustomerId(customerId)
                .stream()
                .map(this.traceabilityDtoMapper::toDto)
                .toList();
    }
}
