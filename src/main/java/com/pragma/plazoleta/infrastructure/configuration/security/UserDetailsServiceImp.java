package com.pragma.plazoleta.infrastructure.configuration.security;

import com.pragma.plazoleta.domain.model.User;
import com.pragma.plazoleta.infrastructure.output.feign.adapter.UserMicroserviceAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImp implements UserDetailsService {

    private final UserMicroserviceAdapter userMicroserviceAdapter;

    @Override
    public UserDetails loadUserByUsername(String emailUser) throws UsernameNotFoundException {
            User user = userMicroserviceAdapter.getUserByEmail(emailUser);
            return new UserDetailsImp(user);

    }
}
