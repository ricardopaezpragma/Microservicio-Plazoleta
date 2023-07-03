package com.pragma.plazoleta.infrastructure.output.jpa.repository;

import com.pragma.plazoleta.infrastructure.output.jpa.entity.RestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {
    Optional<RestaurantEntity> findById(int id);
    Optional<RestaurantEntity> findOneByOwnerId(int ownerId);
}
