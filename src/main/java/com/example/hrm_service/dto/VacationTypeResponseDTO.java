package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VacationTypeResponseDTO {
    private String codeType;
    private String name;
    private String description;
    private boolean isActive;
    private Set<VacationPolicyResponseDTO> policies;
    private Set<VacationBalanceResponseDTO> balances;
    private Set<VacationResponseDTO> vacations;
}
