package com.pragma.plazoleta.application.handler.interfaces;

import com.pragma.plazoleta.application.dto.UserDto;


public interface IUserHandler {
    void saveOwner(UserDto userDto);
    void  saveEmployee(UserDto userDto,String email);
    void saveCustomer(UserDto userDto);

}
