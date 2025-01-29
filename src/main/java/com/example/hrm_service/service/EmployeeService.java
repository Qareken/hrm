package com.example.hrm_service.service;

import com.example.hrm_service.dto.EmployeeDTO;
import com.example.hrm_service.dto.EmployeeResponseWithVacation;
import com.example.hrm_service.dto.FamilyDTO;
import com.example.hrm_service.dto.WorkExperienceDTO;

import com.example.hrm_service.web.EmployeeResponse;
import com.example.hrm_service.web.EmployeeWithPosition;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface EmployeeService {
    EmployeeResponse save(EmployeeDTO employeeDTO);
    EmployeeResponse addExperience(Long id,WorkExperienceDTO workExperience);
    EmployeeResponse addFamily(Long id, FamilyDTO familyDTO);

    EmployeeResponse findById(Long id);
    List<EmployeeWithPosition> findAll();
    Page<EmployeeResponseWithVacation> findByDivision(Long divisionId, Pageable pageable);
}
