package com.pragma.plazoleta.infrastructure.output.feign.feingclient;

import com.pragma.plazoleta.application.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "UserMicroservice", url = "${microservie.url.user}")
public interface IUserMicroserviceFeign {
    @PostMapping("/user")
    void saveUser(@RequestBody UserDto userDto);
    @GetMapping("/user/{userId}")
    Optional<UserDto> getUserId(@PathVariable int userId);
    @GetMapping("/user/user-email/{email}")
    Optional<UserDto> getUserByEmail(@PathVariable String email);
}
