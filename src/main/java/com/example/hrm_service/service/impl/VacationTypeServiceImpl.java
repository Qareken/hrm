package com.example.hrm_service.service.impl;

import com.example.hrm_service.dto.VacationTypeDTO;
import com.example.hrm_service.dto.VacationTypeResponseDTO;
import com.example.hrm_service.entity.VacationType;
import com.example.hrm_service.exception.EntityNotFoundException;
import com.example.hrm_service.mapper.VacationTypeMapper;
import com.example.hrm_service.repository.VacationTypeRepository;
import com.example.hrm_service.service.VacationTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
@RequiredArgsConstructor
public class VacationTypeServiceImpl implements VacationTypeService {
    private final VacationTypeRepository vacationTypeRepository;
    private final VacationTypeMapper vacationTypeMapper;
    @Override
    public VacationTypeResponseDTO save(VacationTypeDTO vacationTypeDTO) {
        VacationType vacationType = vacationTypeMapper.toEntity(vacationTypeDTO);
        if (vacationTypeRepository.existsByCodeType(vacationType.getCodeType())) {
            throw new RuntimeException("Vacation Type Already Exist");
        }
        return vacationTypeMapper.toResponse(vacationTypeRepository.save(vacationType));
    }
    @Override
    public VacationTypeResponseDTO findById(Long id) {
        return vacationTypeMapper.toResponse(vacationTypeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Vacation Type not found with {}", id))));
    }
    @Override
    public VacationTypeResponseDTO findByCodeType(String codeType) {
        return vacationTypeMapper.toResponse(vacationTypeRepository.findByCodeType(codeType)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Vacation Type not found with {}", codeType))));
    }
    @Override
    public VacationTypeResponseDTO update(VacationTypeDTO vacationTypeDTO) {
        VacationType vacationType=vacationTypeRepository.findByCodeType(vacationTypeDTO.getCodeType())
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Vacation Type not found with {}", vacationTypeDTO.getCodeType())));
        vacationTypeMapper.updateFromDto(vacationTypeDTO,vacationType);
        return vacationTypeMapper.toResponse(vacationTypeRepository.save(vacationType));
    }
    @Override
    public VacationTypeResponseDTO changeStatus(String codeType) {
        VacationType vacationType=vacationTypeRepository.findByCodeType(codeType)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Vacation Type not found with {} in order to change status", codeType)));
        vacationType.setActive(false);
        return vacationTypeMapper.toResponse(vacationTypeRepository.save(vacationType));
    }
}
