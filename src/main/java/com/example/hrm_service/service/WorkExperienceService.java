package com.example.hrm_service.service;

import com.example.hrm_service.entity.InternalWorkExperience;

public interface WorkExperienceService {

    void checkForInternalWorkExperience(Long employeeId,InternalWorkExperience internalWorkExperience);
}
