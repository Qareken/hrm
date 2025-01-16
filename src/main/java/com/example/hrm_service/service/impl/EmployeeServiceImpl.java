package com.example.hrm_service.service.impl;

import com.example.hrm_service.dto.*;
import com.example.hrm_service.entity.*;
import com.example.hrm_service.mapper.*;
import com.example.hrm_service.repository.*;
import com.example.hrm_service.service.EmployeeService;
import com.example.hrm_service.web.EmployeeResponse;
import com.example.hrm_service.web.EmployeeWithPosition;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EmployeeServiceImpl  implements EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeResponseMapper employeeResponseMapper;
    private final EmployeeMapper employeeMapper;
    private final FamilyMapper familyMapper;
    private final WorkExperienceMapper workExperienceMapper;
    private final ResponseMapper responseMapper;
    private final DivisionRepository divisionRepository;
    private final PositionRepository positionRepository;
    private final EmployeeFamilyRepository employeeFamilyRepository;
    private final ExternalWorkExperienceRepository externalWorkExperienceRepository;
    private final WorkExperienceServiceImpl workExperienceService;
    @Override
    public EmployeeResponse save(EmployeeDTO employeeDTO) {
        Employee employee = employeeMapper.toEntity(employeeDTO);
        return employeeResponseMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    @Transactional
    public EmployeeResponse addExperience(Long id, WorkExperienceDTO workExperience) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Entity Not Found"));
        if(workExperience instanceof InternalWorkExperienceDTO){
            InternalWorkExperience internalWorkExperience = (InternalWorkExperience) workExperienceMapper.toEntity(workExperience);
            workExperienceService.checkForInternalWorkExperience(employee.getId(),internalWorkExperience);
            internalWorkExperience.setEmployee(employee);
            var optional = divisionRepository.findByCode(internalWorkExperience.getDivision().getCode());
            var position = positionRepository.findByTypePositionAndGrade(internalWorkExperience.getPosition().getTypePosition(), internalWorkExperience.getPosition().getGrade());
            if(position.isEmpty()){
                log.info("is empty position");
                internalWorkExperience.setPosition(positionRepository.save(internalWorkExperience.getPosition()));

            }
            if(optional.isEmpty()){
                internalWorkExperience.setDivision(divisionRepository.save(internalWorkExperience.getDivision()));
                log.info("is empty");
            }else {
                internalWorkExperience.setDivision(optional.get());
            }

            employee.getWorkExperiences().add(internalWorkExperience);
        } else if (workExperience instanceof ExternalWorkExperienceDTO) {
            ExternalWorkExperience experience = (ExternalWorkExperience) workExperienceMapper.toEntity(workExperience);
            experience.setEmployee(employee);
            employee.getWorkExperiences().add(experience);
        }
        employee = employeeRepository.save(employee);
        var response = employeeResponseMapper.toResponse(employee);
        var setExperience = employee.getWorkExperiences().stream().map(workExperienceMapper::toDto).collect(Collectors.toSet());
        response.setWorkExperienceDTO(setExperience);
        var setFamily = employee.getFamilyMember().stream().map(familyMapper::toDto).collect(Collectors.toSet());
        response.setFamilyDTO(setFamily);
        return response;
    }
    @Transactional
    @Override
    public EmployeeResponse addFamily(Long id, FamilyDTO familyDTO) {
        Employee employee = employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Entity Not Found"));
        EmployeeFamily family =familyMapper.toEntity(familyDTO);

        family.setEmployee(employee);
        employee.getFamilyMember().add(family);
        return employeeResponseMapper.toResponse(employeeRepository.save(employee));
    }

    @Override
    public EmployeeResponse findById(Long id) {
        return employeeResponseMapper.toResponse(employeeRepository.findById(id).orElseThrow(()->new RuntimeException("Entity not found")));
    }

    @Override
    public List<EmployeeWithPosition> findAll() {
        return employeeRepository.findAll().stream().map(responseMapper::toEmployeeWithPosition).toList();
    }

}

