package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationBalanceDTO {
    private Long employeeId;
    private Integer vacationTypeId;
    private Integer year;
    private Integer accruedDays;
    private Integer usedDays;
}
