package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.DivisionDTO;
import com.example.hrm_service.entity.Division;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DivisionMapper {

    DivisionMapper INSTANCE = Mappers.getMapper(DivisionMapper.class);


    DivisionDTO toDto(Division entity);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromDto(DivisionDTO dto, @MappingTarget Division entity);
    @InheritInverseConfiguration
    Division toEntity(DivisionDTO dto);
}
