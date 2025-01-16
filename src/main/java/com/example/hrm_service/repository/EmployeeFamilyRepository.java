package com.example.hrm_service.repository;

import com.example.hrm_service.entity.EmployeeFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeFamilyRepository extends JpaRepository<EmployeeFamily, Long> {
}
