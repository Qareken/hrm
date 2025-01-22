package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.VacationPolicyDTO;
import com.example.hrm_service.dto.VacationPolicyResponseDTO;
import com.example.hrm_service.entity.VacationPolicy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationPolicyMapper {

    VacationPolicyMapper INSTANCE = Mappers.getMapper(VacationPolicyMapper.class);
    VacationPolicy toEntity(VacationPolicyDTO dto);
    VacationPolicyResponseDTO toResponse(VacationPolicy entity);
    List<VacationPolicyResponseDTO> toResponseList(List<VacationPolicy> entities);
}
