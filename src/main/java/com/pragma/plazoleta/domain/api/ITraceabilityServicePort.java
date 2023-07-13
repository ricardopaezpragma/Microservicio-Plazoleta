package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.Traceability;

import java.util.List;

public interface ITraceabilityServicePort {
    void saveTraceability(Traceability traceability);

    List<Traceability> getTraceabilityByCustomerId(int customerId);
    List<Traceability> getTraceabilityByOrderId(int orderId);
}
