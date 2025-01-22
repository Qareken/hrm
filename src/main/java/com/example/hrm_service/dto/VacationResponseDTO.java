package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationResponseDTO {
    private Long id;
    private Long employeeId;
    private Integer vacationTypeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalDays;
    private String status;
}
