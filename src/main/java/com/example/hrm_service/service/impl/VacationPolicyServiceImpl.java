package com.example.hrm_service.service.impl;

import com.example.hrm_service.dto.VacationPolicyDTO;
import com.example.hrm_service.dto.VacationPolicyResponseDTO;
import com.example.hrm_service.dto.VacationTypeDTO;
import com.example.hrm_service.entity.VacationPolicy;
import com.example.hrm_service.entity.VacationType;
import com.example.hrm_service.exception.EntityNotFoundException;
import com.example.hrm_service.mapper.VacationPolicyMapper;
import com.example.hrm_service.repository.VacationPolicyRepository;
import com.example.hrm_service.repository.VacationTypeRepository;
import com.example.hrm_service.service.VacationPolicyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationPolicyServiceImpl implements VacationPolicyService {
    private final VacationPolicyMapper vacationPolicyMapper;
    private final VacationPolicyRepository vacationPolicyRepository;
    private final VacationTypeRepository vacationTypeRepository;
    @Override
    public VacationPolicyResponseDTO save(VacationPolicyDTO policyDTO) {
        VacationPolicy vacationPolicy = vacationPolicyMapper.toEntity(policyDTO);
        Long vacationTypeId = Long.valueOf(policyDTO.getVacationTypeId());
        VacationType vacationType =vacationTypeRepository.findById(vacationTypeId)
                .orElseThrow(()->new EntityNotFoundException(MessageFormat.format("Vacation Type id not found with this {} id in order to insert policy", vacationTypeId)));
        vacationPolicy.setVacationType(vacationType);
        return vacationPolicyMapper.toResponse(vacationPolicyRepository.save(vacationPolicy));
    }
    @Override
    public VacationPolicyResponseDTO findById(Long id) {
        return vacationPolicyMapper.toResponse(vacationPolicyRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException(MessageFormat.format("Policy not found with this {} id", id))));
    }
    @Override
    public List<VacationPolicyResponseDTO> findPoliciesByVacationType(VacationTypeDTO typeDTO) {
        return vacationPolicyRepository.findVacationPoliciesByVacationTypeCodeType(typeDTO.getCodeType()).stream().map(vacationPolicyMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public VacationPolicyResponseDTO update(VacationPolicyDTO policyDTO, Long vacationPolicyId) {
        VacationPolicy policy = vacationPolicyRepository.findById(vacationPolicyId)
                .orElseThrow(()->new EntityNotFoundException(MessageFormat.format("Policy not found with this {} id", vacationPolicyId)));
        vacationPolicyMapper.updateFromDto(policyDTO, policy);
        return vacationPolicyMapper.toResponse(vacationPolicyRepository.save(policy));
    }
    @Override
    public void delete(Long id) {
        vacationPolicyRepository.deleteById(id);
    }

}
