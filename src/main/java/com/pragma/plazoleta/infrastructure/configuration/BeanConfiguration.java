package com.pragma.plazoleta.infrastructure.configuration;

import com.pragma.plazoleta.domain.api.IDishServicePort;
import com.pragma.plazoleta.domain.api.IOrderServicePort;
import com.pragma.plazoleta.domain.api.IRestaurantServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.spi.IDishPersistencePort;
import com.pragma.plazoleta.domain.spi.IOrderPersistencePort;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.domain.usecase.DishUseCase;
import com.pragma.plazoleta.domain.usecase.OrderUseCase;
import com.pragma.plazoleta.domain.usecase.RestaurantUseCase;
import com.pragma.plazoleta.domain.usecase.UserUseCase;
import com.pragma.plazoleta.infrastructure.output.feign.Mapper.UserEntityMapper;
import com.pragma.plazoleta.infrastructure.output.feign.adapter.UserMicroserviceAdapter;
import com.pragma.plazoleta.infrastructure.output.feign.feingclient.IUserMicroserviceFeign;
import com.pragma.plazoleta.infrastructure.output.jpa.adapter.DishJpaAdapter;
import com.pragma.plazoleta.infrastructure.output.jpa.adapter.OrderAdapter;
import com.pragma.plazoleta.infrastructure.output.jpa.adapter.RestaurantJpaAdapter;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.DishEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderDishesEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.OrderEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IDishRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderDishesEntityRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IOrderRepository;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IUserMicroserviceFeign userMicroserviceFeign;
    private final UserEntityMapper userEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final IDishRepository dishRepository;
    private final DishEntityMapper dishEntityMapper;
    private final IOrderRepository orderRepository;
    private final OrderEntityMapper orderEntityMapper;
    private final IOrderDishesEntityRepository orderDishesEntityRepository;
    private final OrderDishesEntityMapper orderDishesEntityMapper;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public IUserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserMicroserviceAdapter(userMicroserviceFeign, userEntityMapper, encoder());
    }

    @Bean
    public IRestaurantServicePort restaurantServicePort() {
        return new RestaurantUseCase(restaurantPersistencePort());
    }

    @Bean
    public IRestaurantPersistencePort restaurantPersistencePort() {
        return new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Bean
    public IDishServicePort dishServicePort() {
        return new DishUseCase(dishPersistencePort());
    }

    @Bean
    public IDishPersistencePort dishPersistencePort() {
        return new DishJpaAdapter(dishRepository, dishEntityMapper);
    }

    @Bean
    public IOrderServicePort orderServicePort(){
        return new OrderUseCase(orderPersistencePort());
    }
    @Bean
    public IOrderPersistencePort orderPersistencePort() {
        return new OrderAdapter(orderRepository,orderEntityMapper,orderDishesEntityRepository,orderDishesEntityMapper);
    }
}
