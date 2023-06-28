package com.pragma.plazoleta.infrastructure.output.jpa.repository;

import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderDishesEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.OrderDishesEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDishesEntityRepository extends JpaRepository<OrderDishesEntity, OrderDishesEntityPk> {
}
