package com.example.hrm_service.repository;

import com.example.hrm_service.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findByTypePositionAndGrade(Position.TypePosition typePosition, Integer grade);
}
