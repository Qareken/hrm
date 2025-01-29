package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SickLeaveDTO {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
}
