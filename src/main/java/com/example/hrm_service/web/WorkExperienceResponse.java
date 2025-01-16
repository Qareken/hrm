package com.example.hrm_service.web;

import lombok.*;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
public class WorkExperienceResponse {
    private LocalDateTime start;
    private LocalDateTime endTime;
    private String notes;
    private String organizationName;
    private String position;
}
