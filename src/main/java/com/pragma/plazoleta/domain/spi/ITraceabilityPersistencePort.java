package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.Traceability;

import java.util.List;

public interface ITraceabilityPersistencePort {

    void saveTraceability(Traceability traceability);
    List<Traceability> getTraceabilityByCustomerId(int customerId);
    List<Traceability> getTraceabilityByOrderId(int orderId);
}
