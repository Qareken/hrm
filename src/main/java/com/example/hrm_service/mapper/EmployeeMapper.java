package com.example.hrm_service.mapper;


import com.example.hrm_service.dto.EmployeeDTO;
import com.example.hrm_service.entity.Employee;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {
                FamilyMapper.class,
                WorkExperienceMapper.class
        }
)
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);


    EmployeeDTO toDto(Employee entity);


    @InheritInverseConfiguration

    Employee toEntity(EmployeeDTO dto);

}
