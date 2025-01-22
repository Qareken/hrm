package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.VacationBalanceDTO;
import com.example.hrm_service.dto.VacationBalanceResponseDTO;
import com.example.hrm_service.entity.VacationBalance;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {VacationTypeMapper.class})
public interface VacationBalanceMapper {

    VacationBalanceMapper INSTANCE = Mappers.getMapper(VacationBalanceMapper.class);
    VacationBalance toEntity(VacationBalanceDTO dto);
    VacationBalanceResponseDTO toResponse(VacationBalance entity);
    List<VacationBalanceResponseDTO> toResponseList(List<VacationBalance> entities);
}
