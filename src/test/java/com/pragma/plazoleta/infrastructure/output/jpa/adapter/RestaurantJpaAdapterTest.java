package com.pragma.plazoleta.infrastructure.output.jpa.adapter;

import com.pragma.plazoleta.domain.model.Restaurant;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.mapper.RestaurantEntityMapper;
import com.pragma.plazoleta.infrastructure.output.jpa.repository.IRestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantJpaAdapterTest {
    private RestaurantJpaAdapter restaurantAdapter;

    @Mock
    private IRestaurantRepository restaurantRepository;

    @Mock
    private RestaurantEntityMapper restaurantEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restaurantAdapter = new RestaurantJpaAdapter(restaurantRepository, restaurantEntityMapper);
    }

    @Test
    void testGetAllRestaurants() {
        int page = 0;
        int size = 10;
        Page<RestaurantEntity> restaurantEntityPage = mock(Page.class);
        Page<Restaurant> expectedPage = mock(Page.class);

        when(restaurantRepository.findAll(PageRequest.of(page, size, Sort.by("name")))).thenReturn(restaurantEntityPage);
        when(restaurantEntityMapper.toRestaurantListPage(restaurantEntityPage)).thenReturn(expectedPage);

        Page<Restaurant> result = restaurantAdapter.getAllRestaurants(page, size);

        assertEquals(expectedPage, result);
        verify(restaurantRepository, times(1)).findAll(PageRequest.of(page, size, Sort.by("name")));
        verify(restaurantEntityMapper, times(1)).toRestaurantListPage(restaurantEntityPage);
    }

    @Test
    void testGetRestaurantById() {
        // Mocking
        int restaurantId = 1;
        RestaurantEntity restaurantEntity = new RestaurantEntity();
        restaurantEntity.setId(restaurantId);
        Restaurant expectedRestaurant = new Restaurant(1,"Test","Test",1,"123445","url","1234");
        expectedRestaurant.setId(restaurantId);

        when(restaurantRepository.findById(restaurantId)).thenReturn(Optional.of(restaurantEntity));
        when(restaurantEntityMapper.toRestaurant(restaurantEntity)).thenReturn(expectedRestaurant);

        // Test
        Restaurant actualRestaurant = restaurantAdapter.getRestaurantById(restaurantId);

        // Verification
        assertEquals(expectedRestaurant, actualRestaurant);
    }

    @Test
    void testCreateRestaurant() {
        // Mocking
        Restaurant restaurant = new Restaurant(1,"Test","Test",1,"123445","url","1234");
        RestaurantEntity restaurantEntity = new RestaurantEntity();

        when(restaurantEntityMapper.toEntity(restaurant)).thenReturn(restaurantEntity);

        // Test
        restaurantAdapter.createRestaurant(restaurant);

        // Verification
        verify(restaurantRepository, times(1)).save(restaurantEntity);
    }
}


