package com.pragma.plazoleta.infrastructure.output.jpa.repository;

import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity,Integer> {
    List<OrderEntity> findByCustomerId(int customerId);
    @Query("SELECT o FROM OrderEntity o WHERE o.restaurant.id = :restaurantId")
    List<OrderEntity> findByRestaurantId(@Param("restaurantId") Integer restaurantId);
    Page<OrderEntity> findByStatusAndRestaurantId(String status,int restaurantId, Pageable pageable);
}
