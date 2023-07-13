package com.pragma.plazoleta.infrastructure.output.feign.adapter;

import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.NotFoundException;
import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.pragma.plazoleta.infrastructure.output.feign.mapper.UserEntityMapper;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.IUserMicroserviceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserMicroserviceAdapter implements IUserPersistencePort {
    private final IUserMicroserviceFeign userMicroserviceFeign;
    private final UserEntityMapper userEntityMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(int userId) {
        return userEntityMapper.toUser(userMicroserviceFeign.getUserId(userId)
                .orElseThrow(() -> new NotFoundException("No user found with id: "+userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toUser(userMicroserviceFeign.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("No user found with email: "+email)));
    }

    @Override
    public int getUserIdByEmail(String email) {
        return userMicroserviceFeign.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("No user found with email: "+email))
                .getId();
    }

    @Override
    public User saveUser(User user) {
        userMicroserviceFeign.getUserByEmail(user.getEmail())
                .ifPresent(userEntity -> {
                    throw new UserAlreadyExistException(userEntity.getEmail());
                });
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userEntityMapper.toUser(
                userMicroserviceFeign.saveUser(
                        userEntityMapper.toEntity(user)));
    }
}
