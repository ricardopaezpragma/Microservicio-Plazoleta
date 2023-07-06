package com.pragma.plazoleta.infrastructure.output.feign.adapter;

import com.pragma.plazoleta.domain.model.Traceability;
import com.pragma.plazoleta.domain.spi.ITraceabilityPersistencePort;
import com.pragma.plazoleta.infrastructure.output.feign.Mapper.TraceabilityEntityMapper;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.ITraceabilityMicroservice;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TraceabilityMicroserviceAdapter implements ITraceabilityPersistencePort {
    private final ITraceabilityMicroservice traceabilityMicroservice;
    private final TraceabilityEntityMapper traceabilityEntityMapper;

    @Override
    public void saveTraceability(Traceability traceability) {
        traceabilityMicroservice.saveTraceability(traceabilityEntityMapper.toEntity(traceability));
    }

    @Override
    public List<Traceability> getTraceabilityByCustomerId(int customerId) {
        return traceabilityMicroservice.getTraceabilityByCustomerId(customerId)
                .stream()
                .map(this.traceabilityEntityMapper::toModel)
                .toList();
    }
}
