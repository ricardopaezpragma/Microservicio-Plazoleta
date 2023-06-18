package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.User;

public interface IUserServicePort {
    User getUserByEmail(String email);
    void saveUser(User user);
}
