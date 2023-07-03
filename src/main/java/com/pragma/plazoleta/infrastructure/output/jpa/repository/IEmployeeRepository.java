package com.pragma.plazoleta.infrastructure.output.jpa.repository;

import com.pragma.plazoleta.infrastructure.output.jpa.entity.EmployeeEntity;
import com.pragma.plazoleta.infrastructure.output.jpa.entity.EmployeeEntityPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, EmployeeEntityPk> {
    @Query("SELECT e FROM EmployeeEntity e WHERE e.id.userId = :userId")
    Optional<EmployeeEntity> findByUserId(@Param("userId") int userId);
}
