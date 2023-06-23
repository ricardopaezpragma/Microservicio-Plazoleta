package com.pragma.plazoleta.infrastructure.configuration.security;

import com.pragma.plazoleta.application.dto.UserDto;
import com.pragma.plazoleta.application.handler.IUserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final IUserHandler userHandler;

    @Override
    public UserDetails loadUserByUsername(String emailUser) throws UsernameNotFoundException {
            UserDto userDto = userHandler.getUserByEmail(emailUser);
            return new UserDetailsImp(userDto);

    }
}
