package com.example.hrm_service.service.impl;

import com.example.hrm_service.mapper.SickLeaveMapper;
import com.example.hrm_service.repository.SickLeaveRepository;
import com.example.hrm_service.service.SickLeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SickLeaveServiceImpl implements SickLeaveService {
    private final SickLeaveMapper sickLeaveMapper;
    private final SickLeaveRepository sickLeaveRepository;
}
