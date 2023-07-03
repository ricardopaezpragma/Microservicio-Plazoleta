package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;


public interface IUserHandler {
    void saveOwner(UserDto userDto);
    void  saveEmployee(UserDto userDto,String email);
    void saveCustomer(UserDto userDto);

}
