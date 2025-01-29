package com.example.hrm_service.service.impl;

import com.example.hrm_service.dto.VacationDTO;
import com.example.hrm_service.dto.VacationResponseDTO;
import com.example.hrm_service.entity.*;
import com.example.hrm_service.exception.EntityNotFoundException;
import com.example.hrm_service.mapper.VacationMapper;
import com.example.hrm_service.repository.*;
import com.example.hrm_service.service.VacationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {
    private final VacationMapper vacationMapper;
    private final VacationRepository vacationRepository;
    private final VacationTypeRepository vacationTypeRepository;
    private final EmployeeRepository employeeRepository;
    private final VacationPolicyRepository vacationPolicyRepository;
    private final InternalWorkExperienceRepository workExperienceRepository;
    private final VacationBalanceRepository balanceRepository;
    @Override
    @Transactional
    public VacationResponseDTO save(VacationDTO vacationDTO) {
        Vacation vacation = vacationMapper.toEntity(vacationDTO);
        Long vacationTypeId = Long.valueOf(vacationDTO.getVacationTypeId());
        Long employeeId = vacationDTO.getEmployeeId();
        VacationType vacationType = vacationTypeRepository.findById(vacationTypeId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Vacation Type not found with this {} id", vacationTypeId)));
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Employee not found with this {} id", employeeId)));


        InternalWorkExperience workExperience = workExperienceRepository.findCurrentWorkExperienceByEmployeeId(employeeId)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Experience  not found with this {} employeeId", employeeId)));
        Position.TypePosition position = workExperience.getPosition().getTypePosition();
        VacationPolicy vacationPolicy = vacationPolicyRepository.findByVacationTypeIdAndTypePosition(vacationTypeId, position)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format("Vacation Policy not found with {0} vacation type id and {1} position", vacationTypeId, position.name())));
        vacation.setVacationType(vacationType);
        vacation.setEmployee(employee);
        VacationBalance balance = balanceRepository
                .findByEmployeeIdAndVacationTypeIdAndYear(employeeId, vacationTypeId, vacationDTO.getYear())
                .orElseGet(() -> {
                    VacationBalance newBalance = new VacationBalance();
                    newBalance.setEmployee(employee);
                    newBalance.setVacationType(vacationType);
                    newBalance.setYear(vacationDTO.getYear());
                    newBalance.setAccruedDays(
                            (vacationPolicy.getDefaultDays() != null)
                                    ? vacationPolicy.getDefaultDays()
                                    : 0
                    );
                    newBalance.setUsedDays(0);
                    newBalance.setUsedCount(0);
                    return newBalance;
                });
        checkPolicyAndBalance(vacation, vacationPolicy, balance);
        vacationType.getBalances().add(balance);
        balanceRepository.save(balance);

        return vacationMapper.toResponse(vacationRepository.save(vacation));
    }
    @Override
    public VacationResponseDTO findById(Long id) {
        Vacation vacation = vacationRepository.findById(id).orElseThrow(()->new EntityNotFoundException(MessageFormat.format("Vacation not found with this {} id", id)));
        return vacationMapper.toResponse(vacation);
    }

    @Override
    public List<VacationResponseDTO> findByEmployeeId(Long employeeId) {
        return vacationRepository.findVacationsByEmployeeId(employeeId).stream().map(vacationMapper::toResponse).toList();
    }
    private void checkPolicyAndBalance(Vacation vacation, VacationPolicy policy, VacationBalance balance) {
        if (policy.getDefaultDays() != null
                && vacation.getTotalDays() > policy.getDefaultDays()) {
            throw new RuntimeException("Exceed default days from policy");
        }
        if (!Boolean.TRUE.equals(policy.getUnpaid())) {
            int available = balance.getAccruedDays() - balance.getUsedDays();
            if (available < vacation.getTotalDays()) {
                throw new RuntimeException("Not enough days!");
            }
            balance.setUsedDays(balance.getUsedDays() + vacation.getTotalDays());
        }
        if (Boolean.FALSE.equals(policy.getCanSplit())) {
            if (balance.getUsedCount() != null && balance.getUsedCount() > 0) {
                throw new RuntimeException("Already used this vacation once!");
            }
            balance.setUsedCount(
                    (balance.getUsedCount() == null ? 0 : balance.getUsedCount()) + 1
            );
        }
    }
}
