package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.domain.model.User;

public interface IUserHandler {
    void saveUser(User user);
    void saveOwner(UserDto userDto);
    void  saveEmployee(UserDto userDto);

}
