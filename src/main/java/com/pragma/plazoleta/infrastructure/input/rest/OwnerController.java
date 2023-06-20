package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.DishDto;
import com.pragma.plazoleta.application.dto.DishUpdateDto;
import com.pragma.plazoleta.application.handler.IDishHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/dish")
    public ResponseEntity updateDish(@Valid @RequestBody DishUpdateDto dishUpdateDto){
        dishHandler.updateDish(dishUpdateDto);
        return ResponseEntity.ok().build();
    }
}
