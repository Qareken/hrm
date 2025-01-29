package com.example.hrm_service.service;

import com.example.hrm_service.dto.VacationBalanceResponseDTO;
import com.example.hrm_service.dto.VacationResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface VacationBalanceService {

    List<VacationBalanceResponseDTO> findVacationBalancesByEmployeeId(Long employeeId);
    List<VacationBalanceResponseDTO> findVacationBalanceEmployeeIntervalTime(Long employeeId,Integer year);
}
