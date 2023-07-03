package com.pragma.plazoleta.infrastructure.output.jpa.repository;

import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderDishesEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderDishesEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderDishesEntityRepository extends JpaRepository<OrderDishesEntity, OrderDishesEntityPk> {
    @Query("SELECT o FROM OrderDishesEntity o WHERE o.id.orderId = :orderId")
    List<OrderDishesEntity> findByOrderId(@Param("orderId") Integer orderId);
}
