package com.example.hrm_service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseWithVacation {


    private String firstname;
    private String surname;
    private String lastName;
    private String email;

    private String divisionName;
    private String divisionCode;

    private List<VacationBalanceResponseDTO> balances;

    private List<VacationResponseDTO> vacations;
//
//    // Если нужно показать "не брал ли отпуск"
//    private boolean noVacationForNYears;
}