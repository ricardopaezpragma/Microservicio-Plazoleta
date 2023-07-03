package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.OrderResponseDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ROLE_EMPLEADO')")
@RequiredArgsConstructor
public class EmployeeController {
    private final IOrderHandler orderHandler;
    @GetMapping("/orders/{status}/{page}/{size}")
    public ResponseEntity<Page<OrderResponseDto>> getOrdersByStatus(
            @AuthenticationPrincipal String username,
            @PathVariable String status,
            @PathVariable int page,
            @PathVariable int size
    ){
        return ResponseEntity.ok(orderHandler.getOrdersByStatus(username,status,page,size));
    }
}
