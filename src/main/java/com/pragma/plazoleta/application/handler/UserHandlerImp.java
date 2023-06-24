package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.model.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandlerImp implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;
    @Override
    public void saveOwner(UserDto userDto) {
        User user= userDtoMapper.userDtoToUser(userDto);
        user.setRole("PROPIETARIO");
        userServicePort.saveUser(user);
    }

    @Override
    public void saveEmployee(UserDto userDto) {
        User user= userDtoMapper.userDtoToUser(userDto);
        user.setRole("EMPLEADO");
        userServicePort.saveUser(user);
    }

    @Override
    public void saveCustomer(UserDto userDto) {
        User user= userDtoMapper.userDtoToUser(userDto);
        user.setRole("CLIENTE");
        userServicePort.saveUser(user);
    }
}
