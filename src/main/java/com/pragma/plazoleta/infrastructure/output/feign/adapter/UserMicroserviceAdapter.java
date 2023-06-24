package com.pragma.plazoleta.infrastructure.output.feign.adapter;

import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.UserAlreadyExistException;
import com.pragma.plazoleta.infrastructure.exception.UserNotFoundException;
import com.pragma.plazoleta.infrastructure.output.feign.Mapper.UserEntityMapper;
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
                .orElseThrow(() -> new UserNotFoundException(userId)));
    }

    @Override
    public User getUserByEmail(String email) {
        return userEntityMapper.toUser(userMicroserviceFeign.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email)));
    }

    @Override
    public int getUserIdByEmail(String email) {
        return userMicroserviceFeign.getUserByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(email))
                .getId();
    }

    @Override
    public void saveUser(User user) {
        userMicroserviceFeign.getUserByEmail(user.getEmail())
                .ifPresentOrElse(userEntity -> {
                    throw new UserAlreadyExistException(userEntity.getEmail());
                }, () -> {
                    user.setPassword(passwordEncoder.encode(user.getPassword()));
                    userMicroserviceFeign.saveUser(userEntityMapper.toEntity(user));
                });
    }
}
