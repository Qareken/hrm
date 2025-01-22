package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationPolicyResponseDTO {

    private Integer vacationTypeId;
    private Integer defaultDays;
    private Boolean workingDays;
    private Boolean canSplit;
    private Integer maxSplits;
    private Integer daysPerFiveYears;
    private Boolean unpaid;
    private Boolean attestation;
}
