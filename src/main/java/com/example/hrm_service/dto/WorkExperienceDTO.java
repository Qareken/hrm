package com.example.hrm_service.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME, // Use a type name to differentiate subclasses
        include = JsonTypeInfo.As.PROPERTY, // Include the type information as a property
        property = "type" // The property to indicate the subclass type
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = InternalWorkExperienceDTO.class, name = "internal"),
        @JsonSubTypes.Type(value = ExternalWorkExperienceDTO.class, name = "external")
})
public abstract class  WorkExperienceDTO {

    private LocalDateTime start;
    private LocalDateTime endTime;
    private String notes;
}
