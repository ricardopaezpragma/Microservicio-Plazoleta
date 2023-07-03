package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.domain.spi.IRestaurantPersistencePort;
import com.pragma.plazoleta.infrastructure.exception.RestaurantNotFoundException;
import com.pragma.plazoleta.infrastructure.exception.UserNotFoundException;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@RequiredArgsConstructor
public class RestaurantJpaAdapter implements IRestaurantPersistencePort {

    private final IRestaurantRepository restaurantRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;

    @Override
    public Page<Restaurant> getAllRestaurants(int page, int size) {
        return restaurantEntityMapper.toRestaurantListPage(restaurantRepository
                .findAll(PageRequest.of(page,size, Sort.by("name"))));
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findById(restaurantId)
                .orElseThrow(()->new RestaurantNotFoundException(restaurantId)));
    }

    @Override
    public Restaurant getRestaurantByOwnerId(int ownerId) {
        return restaurantEntityMapper.toRestaurant(restaurantRepository.findOneByOwnerId(ownerId)
                .orElseThrow(()->new UserNotFoundException(ownerId)));
    }

    @Override
    public void createRestaurant(Restaurant restaurant) {
        restaurantRepository.save(restaurantEntityMapper.toEntity(restaurant));
    }
}
