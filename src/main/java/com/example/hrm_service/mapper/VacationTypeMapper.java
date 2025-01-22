package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.VacationTypeDTO;
import com.example.hrm_service.dto.VacationTypeResponseDTO;
import com.example.hrm_service.entity.VacationType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface VacationTypeMapper {
    VacationTypeMapper INSTANCE = Mappers.getMapper(VacationTypeMapper.class);
    VacationType toEntity(VacationTypeDTO dto);
    VacationTypeResponseDTO toResponse(VacationType entity);
    List<VacationTypeResponseDTO> toResponseList(List<VacationType> entities);
}
