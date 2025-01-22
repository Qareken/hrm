package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.SickLeaveDTO;
import com.example.hrm_service.dto.SickLeaveResponseDTO;
import com.example.hrm_service.entity.SickLeave;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SickLeaveMapper {

    SickLeaveMapper INSTANCE = Mappers.getMapper(SickLeaveMapper.class);
    SickLeave toEntity(SickLeaveDTO dto);
    SickLeaveResponseDTO toResponse(SickLeave entity);
    List<SickLeaveResponseDTO> toResponseList(List<SickLeave> entities);
}
