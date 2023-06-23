package com.pragma.plazoleta.application.handler;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandlerImp implements IUserHandler {
    private final IUserServicePort userServicePort;
    private final UserDtoMapper userDtoMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        return userDtoMapper.userToUserDto(userServicePort.getUserByEmail(email));
    }

    @Override
    public void saveOwner(UserDto userDto) {
        userDto.setRole("Propietario");
        this.saveUser(userDto);
    }

    @Override
    public void saveEmployee(UserDto userDto) {
        userDto.setRole("Empleado");
        this.saveUser(userDto);
    }

    @Override
    public void saveUser(UserDto userDto) {
        userServicePort.saveUser(userDtoMapper.userDtoToUser(userDto));
    }
}
