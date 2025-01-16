package com.example.hrm_service.service.impl;

import com.example.hrm_service.dto.DivisionDTO;
import com.example.hrm_service.entity.Division;
import com.example.hrm_service.entity.Position;
import com.example.hrm_service.mapper.DivisionMapper;
import com.example.hrm_service.mapper.ResponseMapper;
import com.example.hrm_service.repository.DivisionRepository;
import com.example.hrm_service.repository.EmployeeRepository;
import com.example.hrm_service.service.DivisionService;
import com.example.hrm_service.web.DivisionResponse;
import com.example.hrm_service.web.EmployeeWithPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionServiceImpl implements DivisionService {
    private final DivisionRepository divisionRepository;
    private final EmployeeRepository employeeRepository;
    private final DivisionMapper divisionMapper;
    private final ResponseMapper responseMapper;


    @Override
    public DivisionResponse save(DivisionDTO divisionDTO) {
        var division = divisionMapper.toEntity(divisionDTO);
        return responseMapper.toDivisionResponse(divisionRepository.save(division));
    }

    @Override
    public DivisionResponse update(DivisionDTO divisionDTO) {
//
       var existedDivision = divisionRepository.findByCode(divisionDTO.getCode()).orElseThrow(()->new RuntimeException("Entity not found"));
       divisionMapper.updateFromDto(divisionDTO, existedDivision);
        return responseMapper.toDivisionResponse(divisionRepository.save(existedDivision));
    }

    @Override
    public void delete(Long id) {
        divisionRepository.deleteById(id);
    }

    @Override
    public List<DivisionResponse> findAll() {
        return divisionRepository.findAll().stream().map(responseMapper::toDivisionResponse).toList();
    }

    @Override
    public DivisionResponse findById(Long id) {
        var division = divisionRepository.findById(id).orElseThrow(() -> new RuntimeException("Division Entity not found"));
        List<EmployeeWithPosition> staff = employeeRepository.findEmployeesWithPositionByDivision(id);
        String head = String.valueOf(staff.stream().filter(st-> st.getPosition().equals(Position.TypePosition.HEAD)).findFirst().map(EmployeeWithPosition::getName).isPresent());
        String clerk = String.valueOf(staff.stream().filter(st-> st.getPosition().equals(Position.TypePosition.CLERK)).findFirst());
        var response =responseMapper.toDivisionResponse(division);
        response.setClerk(clerk);
        response.setHead(head);
        response.setEmployeeResponses(staff);
        return response;
    }
}
