package com.example.hrm_service.repository;

import com.example.hrm_service.entity.ExternalWorkExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExternalWorkExperienceRepository extends JpaRepository<ExternalWorkExperience, Long> {
}
