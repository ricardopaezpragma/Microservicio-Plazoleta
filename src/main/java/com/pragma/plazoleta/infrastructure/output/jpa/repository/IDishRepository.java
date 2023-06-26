package com.pragma.plazoleta.infrastructure.output.jpa.repository;

import com.pragma.plazoleta.infrastructure.output.jpa.entity.DishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IDishRepository extends JpaRepository<DishEntity,Integer> {
    @Override
    Optional<DishEntity> findById(Integer integer);
    Page<DishEntity> findAllByRestaurantIdAndCategoryIdAndIsActive(int restaurantId, int categoryId,boolean isActive, Pageable pageable);

}
