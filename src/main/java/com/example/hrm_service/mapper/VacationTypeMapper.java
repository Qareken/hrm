package com.example.hrm_service.mapper;


import com.example.hrm_service.dto.VacationTypeDTO;
import com.example.hrm_service.dto.VacationTypeResponseDTO;

import com.example.hrm_service.entity.VacationType;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {VacationMapper.class, VacationPolicyMapper.class, VacationBalanceMapper.class})
public interface VacationTypeMapper {
    VacationTypeMapper INSTANCE = Mappers.getMapper(VacationTypeMapper.class);
    VacationType toEntity(VacationTypeDTO dto);
//    @Mapping(target = "vacations", source = "vacations") // Явно указываем
//    @Mapping(target = "policies", source = "policies")
//    @Mapping(target = "balances", source = "balances")
    VacationTypeResponseDTO toResponse(VacationType entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(VacationTypeDTO dto, @MappingTarget VacationType entity);
    List<VacationTypeResponseDTO> toResponseList(List<VacationType> entities);
}
