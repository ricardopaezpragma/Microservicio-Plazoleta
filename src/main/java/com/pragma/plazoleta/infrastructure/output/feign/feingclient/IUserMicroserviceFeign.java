package com.pragma.plazoleta.infrastructure.output.feign.feingclient;

import com.pragma.plazoleta.infrastructure.output.feign.Entity.UserEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@FeignClient(name = "UserMicroservice", url = "${microservice.url.user}")
public interface IUserMicroserviceFeign {
    @PostMapping("/user")
    UserEntity saveUser(@RequestBody UserEntity userEntity);

    @GetMapping("/user/{userId}")
    Optional<UserEntity> getUserId(@PathVariable int userId);

    @GetMapping("/user/email/{email}")
    Optional<UserEntity> getUserByEmail(@PathVariable String email);
}
