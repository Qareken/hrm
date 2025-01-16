package com.example.hrm_service.service;

import com.example.hrm_service.dto.EmployeeDTO;
import com.example.hrm_service.dto.FamilyDTO;
import com.example.hrm_service.dto.WorkExperienceDTO;
import com.example.hrm_service.entity.WorkExperience;
import com.example.hrm_service.web.EmployeeResponse;
import com.example.hrm_service.web.EmployeeWithPosition;
import com.example.hrm_service.web.WorkExperienceResponse;

import java.util.List;

public interface EmployeeService {
    EmployeeResponse save(EmployeeDTO employeeDTO);
    EmployeeResponse addExperience(Long id,WorkExperienceDTO workExperience);
    EmployeeResponse addFamily(Long id, FamilyDTO familyDTO);

    EmployeeResponse findById(Long id);
    List<EmployeeWithPosition> findAll();
}
