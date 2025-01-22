package com.example.hrm_service.dto;

import java.util.List;

public class VacationTypeResponseDTO {
    private String codeType;
    private String name;
    private String description;
    private boolean isActive;
    private List<VacationPolicyResponseDTO> policies;
    private List<VacationBalanceResponseDTO> balances;
    private List<VacationResponseDTO> vacations;
}
