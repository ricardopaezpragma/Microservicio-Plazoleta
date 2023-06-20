package com.pragma.plazoleta.infrastructure.output.feign.adapter;

import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.pragma.plazoleta.infrastructure.exception.UserNotFoundException;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.IUserMicroserviceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMicroserviceAdapter implements IUserPersistencePort {
    private final IUserMicroserviceFeign userMicroserviceFeign;
    private final UserDtoMapper userDtoMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(int userId) {
        return userDtoMapper.userDtoToUser(userMicroserviceFeign.getUserId(userId)
                .orElseThrow(()-> new UserNotFoundException(userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userDtoMapper.userDtoToUser(userMicroserviceFeign.getUserByEmail(email)
                .orElseThrow(()-> new UserNotFoundException(email)));
    }

    @Override
    public void saveUser(User user) {
        userMicroserviceFeign.getUserByEmail(user.getEmail())
                .ifPresentOrElse(userDto -> {
                    throw new UserAlreadyExistException(userDto.getEmail());
                }, () -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userMicroserviceFeign.saveUser(userDtoMapper.userToUserDto(user));
                });
    }
}
