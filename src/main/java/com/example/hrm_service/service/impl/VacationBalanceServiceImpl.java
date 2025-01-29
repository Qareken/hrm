package com.example.hrm_service.service.impl;

import com.example.hrm_service.dto.VacationBalanceResponseDTO;
import com.example.hrm_service.mapper.VacationBalanceMapper;
import com.example.hrm_service.repository.VacationBalanceRepository;
import com.example.hrm_service.service.VacationBalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationBalanceServiceImpl implements VacationBalanceService {
    private final VacationBalanceMapper vacationBalanceMapper;
    private final VacationBalanceRepository vacationBalanceRepository;
    @Override
    public List<VacationBalanceResponseDTO> findVacationBalancesByEmployeeId(Long employeeId) {
        return vacationBalanceRepository.findVacationBalancesByEmployeeId(employeeId).stream().map(vacationBalanceMapper::toResponse).collect(Collectors.toList());
    }
    @Override
    public List<VacationBalanceResponseDTO> findVacationBalanceEmployeeIntervalTime(Long employeeId, Integer year) {
        return vacationBalanceRepository.findVacationBalancesByEmployeeIdAndYear(employeeId, year).stream().map(vacationBalanceMapper::toResponse).toList();
    }
}
