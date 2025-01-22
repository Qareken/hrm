package com.example.hrm_service.mapper;

import com.example.hrm_service.dto.BusinessTripDTO;
import com.example.hrm_service.dto.BusinessTripResponseDTO;
import com.example.hrm_service.entity.BusinessTrip;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BusinessTripMapper {
    BusinessTripMapper INSTANCE = Mappers.getMapper(BusinessTripMapper.class);
    BusinessTrip toEntity(BusinessTripDTO dto);
    BusinessTripResponseDTO toResponse(BusinessTrip entity);
}
