package com.example.hrm_service.service;

import com.example.hrm_service.dto.VacationPolicyDTO;
import com.example.hrm_service.dto.VacationPolicyResponseDTO;
import com.example.hrm_service.dto.VacationTypeDTO;

import java.util.List;

public interface VacationPolicyService {
    VacationPolicyResponseDTO save(VacationPolicyDTO policyDTO);
    VacationPolicyResponseDTO findById(Long id);

    List<VacationPolicyResponseDTO> findPoliciesByVacationType(VacationTypeDTO typeDTO);
    VacationPolicyResponseDTO update (VacationPolicyDTO policyDTO, Long vacationPolicyId);

    void delete (Long id);

}
