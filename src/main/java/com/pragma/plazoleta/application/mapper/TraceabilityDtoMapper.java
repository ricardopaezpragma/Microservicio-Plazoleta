package com.pragma.plazoleta.application.mapper;

import com.pragma.plazoleta.application.dto.TraceabilityDto;
import com.pragma.plazoleta.domain.model.Traceability;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TraceabilityDtoMapper {
    Traceability toModel(TraceabilityDto traceabilityDto);
    TraceabilityDto toDto(Traceability traceability);
}
