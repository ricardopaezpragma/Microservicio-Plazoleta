package com.pragma.plazoleta.domain.api;

import com.pragma.plazoleta.domain.model.User;

public interface IUserServicePort {
    User getUserById(int userId);
    User getUserByEmail(String email);
    int getUserIdByEmail(String email);
    void saveUser(User user);
}
