package com.pragma.plazoleta.domain.spi;

import com.pragma.plazoleta.domain.model.User;

public interface IUserPersistencePort {
    User getUserByEmail(String email);
    void saveUser(User user);
}
