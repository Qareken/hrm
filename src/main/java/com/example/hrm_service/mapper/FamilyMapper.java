package com.example.hrm_service.mapper;



import com.example.hrm_service.dto.FamilyDTO;
import com.example.hrm_service.entity.EmployeeFamily;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface FamilyMapper {

    FamilyMapper INSTANCE = Mappers.getMapper(FamilyMapper.class);


    FamilyDTO toDto(EmployeeFamily entity);


    EmployeeFamily toEntity(FamilyDTO dto);

}
