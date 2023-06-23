package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.RestaurantDto;
import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('Administrador')")
@RequiredArgsConstructor
public class AdminController {

    private final IUserHandler userHandler;
    private final IRestaurantHandler restaurantHandler;
    @PostMapping("/owner")
    public ResponseEntity saveOwner(@Valid @RequestBody UserDto userDto){
        userHandler.saveOwner(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };
    @PostMapping("/restaurant")
    public ResponseEntity crateRestaurant(@Valid @RequestBody RestaurantDto restaurantDto){
        restaurantHandler.createRestaurant(restaurantDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };
}
