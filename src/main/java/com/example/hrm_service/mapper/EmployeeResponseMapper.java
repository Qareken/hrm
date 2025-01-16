package com.example.hrm_service.mapper;

import com.example.hrm_service.entity.Employee;
import com.example.hrm_service.web.EmployeeResponse;
import org.mapstruct.Mapper;

import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {WorkExperienceMapper.class, FamilyMapper.class}
)
public interface EmployeeResponseMapper {
    EmployeeResponseMapper INSTANCE = Mappers.getMapper(EmployeeResponseMapper.class);

    EmployeeResponse toResponse(Employee entity);
}