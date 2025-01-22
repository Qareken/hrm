package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationTypeDTO {
    private String codeType;
    private String name;
    private String description;
    private boolean isActive = true;
}
