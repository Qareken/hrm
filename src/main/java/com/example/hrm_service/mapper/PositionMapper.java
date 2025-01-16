package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.PositionDTO;
import com.example.hrm_service.entity.Position;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionMapper {

    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "grade", source = "grade")
//    @Mapping(target = "typePosition", source = "typePosition")
    PositionDTO toDto(Position entity);

//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "grade", source = "grade")
//    @Mapping(target = "typePosition", source = "typePosition")
    Position toEntity(PositionDTO dto);
}