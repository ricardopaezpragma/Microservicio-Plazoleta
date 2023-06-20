package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owner")
public class OwnerController {

    private final IDishHandler dishHandler;

    @PostMapping("/dish")
    public ResponseEntity createDish(@Valid  @RequestBody DishDto dishDto){
        dishHandler.createDish(dishDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
