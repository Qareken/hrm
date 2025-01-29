package com.example.hrm_service.repository;

import com.example.hrm_service.entity.Position;
import com.example.hrm_service.entity.VacationPolicy;
import com.example.hrm_service.entity.VacationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacationPolicyRepository extends JpaRepository<VacationPolicy, Long> {
    Optional<VacationPolicy> findByVacationTypeIdAndTypePosition(Long vacationTypeId, Position.TypePosition typePosition);
    List<VacationPolicy> findVacationPoliciesByVacationTypeCodeType(String vacationCode);

}
