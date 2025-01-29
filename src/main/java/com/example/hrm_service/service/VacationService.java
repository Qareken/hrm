package com.example.hrm_service.service;

import com.example.hrm_service.dto.VacationDTO;
import com.example.hrm_service.dto.VacationResponseDTO;

import java.util.List;

public interface VacationService {
    VacationResponseDTO save(VacationDTO vacationDTO);
    VacationResponseDTO findById(Long id);
    List<VacationResponseDTO> findByEmployeeId(Long employeeId);


}
