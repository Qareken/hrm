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

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "firstname", source = "firstname")
//    @Mapping(target = "surname", source = "surname")
//    @Mapping(target = "lastName", source = "lastName")
//    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
//    @Mapping(target = "phoneNumber", source = "phoneNumber")
//    @Mapping(target = "email", source = "email")
//    @Mapping(target = "numberOfPassport", source = "numberOfPassport")
//    @Mapping(target = "address", source = "address")

    EmployeeDTO toDto(Employee entity);


    @InheritInverseConfiguration

    Employee toEntity(EmployeeDTO dto);

}
