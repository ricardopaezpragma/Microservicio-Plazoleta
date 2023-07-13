package com.pragma.plazoleta.domain.usecase;

import com.pragma.plazoleta.domain.api.ITraceabilityServicePort;
import com.pragma.plazoleta.domain.model.Traceability;
import com.pragma.plazoleta.domain.spi.ITraceabilityPersistencePort;

import java.util.List;

public class TraceabilityUseCase implements ITraceabilityServicePort {
    private final ITraceabilityPersistencePort traceabilityPersistencePort;

    public TraceabilityUseCase(ITraceabilityPersistencePort traceabilityPersistencePort) {
        this.traceabilityPersistencePort = traceabilityPersistencePort;
    }

    @Override
    public void saveTraceability(Traceability traceability) {
        traceabilityPersistencePort.saveTraceability(traceability);
    }

    @Override
    public List<Traceability> getTraceabilityByCustomerId(int customerId) {
        return traceabilityPersistencePort.getTraceabilityByCustomerId(customerId);
    }

    @Override
    public List<Traceability> getTraceabilityByOrderId(int orderId) {
        return traceabilityPersistencePort.getTraceabilityByOrderId(orderId);
    }
}
