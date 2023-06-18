package com.pragma.plazoleta.infrastructure.input.rest;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.IUserHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final IUserHandler userHandler;
    @PostMapping("/owner")
    public ResponseEntity saveOwner(@Valid @RequestBody UserDto userDto){
        userHandler.saveOwner(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    };

}
