package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.VacationDTO;
import com.example.hrm_service.dto.VacationResponseDTO;
import com.example.hrm_service.entity.Vacation;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {VacationTypeMapper.class})
public interface VacationMapper {

    VacationMapper INSTANCE = Mappers.getMapper(VacationMapper.class);
    Vacation toEntity(VacationDTO dto);
    VacationResponseDTO toResponse(Vacation entity);
    List<VacationResponseDTO> toResponseList(List<Vacation> entities);
}

