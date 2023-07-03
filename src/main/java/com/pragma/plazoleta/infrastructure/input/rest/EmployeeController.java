package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.OrderResponseDto;
import com.pragma.plazoleta.application.handler.IOrderHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    ) {
        return ResponseEntity.ok(orderHandler.getOrdersByStatus(username, status, page, size));
    }

    @PostMapping("/orders/making")
    public ResponseEntity<List<OrderResponseDto>> setOrderInMaking(@AuthenticationPrincipal String username,
                                                                   @RequestBody List<Integer> ordersIdList) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderHandler.setOrderInMaking(username, ordersIdList));
    }
}
