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

    PositionDTO toDto(Position entity);


    Position toEntity(PositionDTO dto);
}