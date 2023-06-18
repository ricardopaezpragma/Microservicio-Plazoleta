package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;

public interface IUserHandler {
    UserDto getUserByEmail(String email);
    void saveUser(UserDto userDto);
    UserDto saveOwner(UserDto userDto);
}
