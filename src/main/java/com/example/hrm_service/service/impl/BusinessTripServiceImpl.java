package com.example.hrm_service.service.impl;

import com.example.hrm_service.mapper.BusinessTripMapper;
import com.example.hrm_service.repository.BusinessTripRepository;
import com.example.hrm_service.service.BusinessTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessTripServiceImpl implements BusinessTripService {
    private final BusinessTripMapper businessTripMapper;
    private final BusinessTripRepository businessTripRepository;
}
