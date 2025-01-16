package com.example.hrm_service.service.impl;

import com.example.hrm_service.entity.InternalWorkExperience;
import com.example.hrm_service.entity.Position;
import com.example.hrm_service.repository.InternalWorkExperienceRepository;
import com.example.hrm_service.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkExperienceServiceImpl implements WorkExperienceService {
    private final InternalWorkExperienceRepository internalWorkExperienceRepository;
    @Override
    public void checkForInternalWorkExperience(Long employeeId,InternalWorkExperience internalWorkExperience) {
        if(internalWorkExperienceRepository.findByEmployeeDivisionPosition(employeeId, internalWorkExperience.getDivision().getCode(), internalWorkExperience.getPosition().getTypePosition(),internalWorkExperience.getPosition().getGrade()).isPresent()){
            throw new RuntimeException("Experience already exist");
        }
    }

}
