package com.example.hrm_service.repository;

import com.example.hrm_service.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {
    List<Vacation> findVacationsByEmployeeId(Long employeeId);
}
