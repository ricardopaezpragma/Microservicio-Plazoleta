package com.pragma.plazoleta.infrastructure.output.feign.entity;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TraceabilityEntity {

    private Integer orderId;
    private Integer customerId;
    private String customerEmail;
    private LocalDateTime date;
    private String previousState;
    private String newState;
    private Integer employeeId;
    private String employeeEmail;
}
