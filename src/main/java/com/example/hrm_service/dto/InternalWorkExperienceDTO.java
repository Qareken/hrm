package com.example.hrm_service.dto;


import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

@NoArgsConstructor
public class InternalWorkExperienceDTO extends WorkExperienceDTO {


    private DivisionDTO division;
    private PositionDTO position;
}
