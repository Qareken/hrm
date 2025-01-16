package com.example.hrm_service.service;

import com.example.hrm_service.dto.DivisionDTO;
import com.example.hrm_service.web.DivisionResponse;

import java.util.List;

public interface DivisionService {

    DivisionResponse save(DivisionDTO divisionDTO);
    DivisionResponse update(DivisionDTO divisionDTO);

    void delete(Long id);
    List<DivisionResponse> findAll();
    DivisionResponse findById(Long id);
}
