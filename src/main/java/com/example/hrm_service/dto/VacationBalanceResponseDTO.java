package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationBalanceResponseDTO {

    private Long employeeId;
    private Integer vacationTypeId;

    private Integer year;
    private Integer accruedDays;
    private Integer usedDays;
}
