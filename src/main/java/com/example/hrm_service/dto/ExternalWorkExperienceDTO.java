package com.example.hrm_service.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)

@NoArgsConstructor
public class ExternalWorkExperienceDTO extends WorkExperienceDTO {

    private String externalOrganizationName;
    private String externalPosition;
}
