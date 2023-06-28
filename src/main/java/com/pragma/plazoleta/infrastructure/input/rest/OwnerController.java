package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner")
@PreAuthorize("hasRole('ROLE_PROPIETARIO')")
@RequiredArgsConstructor
public class OwnerController {

    private final IDishHandler dishHandler;
    private final IUserHandler userHandler;

    @PostMapping("/dish")
    public ResponseEntity<HttpStatus> createDish(@Valid @RequestBody DishDto dishDto,@AuthenticationPrincipal String username) {
        dishHandler.createDish(username,dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/dish")
    public ResponseEntity<HttpStatus> updateDish(@Valid @RequestBody DishUpdateDto dishUpdateDto,@AuthenticationPrincipal String username) {
        dishHandler.updateDish(username,dishUpdateDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/employee")
    public ResponseEntity<HttpStatus> saveEmployee(@Valid @RequestBody UserDto userDto) {
        userHandler.saveEmployee(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/toggleDishStatus/{dishId}")
    public ResponseEntity<HttpStatus> toggleDishStatus(@PathVariable int dishId, @AuthenticationPrincipal String username) {
        dishHandler.toggleDishStatus(username,dishId);
        return ResponseEntity.ok().build();
    }
}
