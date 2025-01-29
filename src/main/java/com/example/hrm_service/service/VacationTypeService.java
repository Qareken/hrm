package com.example.hrm_service.service;

import com.example.hrm_service.dto.VacationTypeDTO;
import com.example.hrm_service.dto.VacationTypeResponseDTO;
import com.example.hrm_service.entity.VacationType;

public interface VacationTypeService {

    VacationTypeResponseDTO save (VacationTypeDTO vacationTypeDTO);
    VacationTypeResponseDTO findById(Long id);
    VacationTypeResponseDTO findByCodeType(String codeType);
    VacationTypeResponseDTO update(VacationTypeDTO vacationTypeDTO);
    VacationTypeResponseDTO changeStatus(String codeType);
}
