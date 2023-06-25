package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.RestaurantResponse;
import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.IRestaurantHandler;
import com.pragma.plazoleta.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final IUserHandler userHandler;
    private final IRestaurantHandler restaurantHandler;

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signup(@RequestBody UserDto userDto){
        userHandler.saveEmployee(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/restaurant/{page}/{size}")
    public ResponseEntity<Page<RestaurantResponse>> getAllRestaurants(@PathVariable int page, @PathVariable int size){
        return ResponseEntity.ok(restaurantHandler.getAllRestaurants(page,size));
    }
}
