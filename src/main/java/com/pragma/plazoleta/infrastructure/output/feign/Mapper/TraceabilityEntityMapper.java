package com.pragma.plazoleta.infrastructure.output.feign.mapper;

import com.pragma.plazoleta.domain.model.Traceability;
import com.pragma.plazoleta.infrastructure.output.feign.entity.TraceabilityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TraceabilityEntityMapper {
    Traceability toModel(TraceabilityEntity traceabilityEntity);
    TraceabilityEntity toEntity(Traceability traceability);
}
