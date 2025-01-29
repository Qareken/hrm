package com.example.hrm_service.repository;

import com.example.hrm_service.entity.VacationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VacationTypeRepository extends JpaRepository<VacationType, Long> {
    boolean existsByCodeType(String codeType);
    Optional<VacationType> findByCodeType(String code);
}
