package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.ExternalWorkExperienceDTO;
import com.example.hrm_service.dto.InternalWorkExperienceDTO;
import com.example.hrm_service.dto.WorkExperienceDTO;
import com.example.hrm_service.entity.ExternalWorkExperience;
import com.example.hrm_service.entity.InternalWorkExperience;
import com.example.hrm_service.entity.WorkExperience;
import com.example.hrm_service.mapper.DivisionMapper;
import com.example.hrm_service.mapper.PositionMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {DivisionMapper.class, PositionMapper.class}
)
public interface WorkExperienceMapper {
    WorkExperienceMapper INSTANCE = Mappers.getMapper(WorkExperienceMapper.class);

    @Mapping(target = "externalOrganizationName", source = "externalOrganizationName")
    @Mapping(target = "externalPosition", source = "externalPosition")
    ExternalWorkExperienceDTO toDto(ExternalWorkExperience entity);

    @InheritInverseConfiguration
    ExternalWorkExperience toEntity(ExternalWorkExperienceDTO dto);


    @Mapping(target = "division", source = "division")
    @Mapping(target = "position", source = "position")
    InternalWorkExperienceDTO toDto(InternalWorkExperience entity);

    @InheritInverseConfiguration
    InternalWorkExperience toEntity(InternalWorkExperienceDTO dto);

    default WorkExperienceDTO toDto(WorkExperience entity) {
        if (entity instanceof ExternalWorkExperience) {
            return toDto((ExternalWorkExperience) entity);
        } else if (entity instanceof InternalWorkExperience) {
            return toDto((InternalWorkExperience) entity);
        }
        return null;
    }

    default WorkExperience toEntity(WorkExperienceDTO dto) {
        if (dto instanceof ExternalWorkExperienceDTO) {
            return toEntity((ExternalWorkExperienceDTO) dto);
        } else if (dto instanceof InternalWorkExperienceDTO) {
            return toEntity((InternalWorkExperienceDTO) dto);
        }
        return null;
    }
}
