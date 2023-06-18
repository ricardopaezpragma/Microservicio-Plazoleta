package com.pragma.plazoleta.infrastructure.configuration;

import com.pragma.plazoleta.application.mapper.UserDtoMapper;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.domain.usecase.UserUseCase;
import com.pragma.plazoleta.infrastructure.output.feign.adapter.UserMicroserviceAdapter;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.IUserMicroserviceFeign;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserMicroserviceFeign userMicroserviceFeign;
    private final UserDtoMapper userDtoMapper;
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
   @Bean
   public IUserServicePort userServicePort(){
       return new UserUseCase(userPersistencePort());
   }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserMicroserviceAdapter(userMicroserviceFeign, userDtoMapper,encoder());
    }
}
