package com.example.hrm_service.repository;

import com.example.hrm_service.entity.VacationBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VacationBalanceRepository extends JpaRepository<VacationBalance, Long> {
    Optional<VacationBalance> findByEmployeeIdAndVacationTypeIdAndYear(Long employeeId, Long vacationTypeId, int currentYear);
    List<VacationBalance> findVacationBalancesByEmployeeIdAndYear(Long employeeId, Integer year);
    List<VacationBalance> findVacationBalancesByEmployeeId(Long employeeId);
}
