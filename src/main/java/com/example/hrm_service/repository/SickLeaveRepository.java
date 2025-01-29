package com.example.hrm_service.repository;

import com.example.hrm_service.entity.SickLeave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SickLeaveRepository extends JpaRepository<SickLeave, Long> {
}
