package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTripDTO {
    private Long employeeId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String destination;
    private String purpose;
    private String attachmentPath;
    private String status;
}
