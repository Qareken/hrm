package com.example.hrm_service.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("EXTERNAL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExternalWorkExperience extends WorkExperience{
    private String externalOrganizationName;
    private String externalPosition;
}
