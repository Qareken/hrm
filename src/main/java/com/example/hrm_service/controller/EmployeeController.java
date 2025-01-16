package com.example.hrm_service.controller;

import com.example.hrm_service.dto.EmployeeDTO;
import com.example.hrm_service.dto.FamilyDTO;
import com.example.hrm_service.dto.WorkExperienceDTO;
import com.example.hrm_service.service.impl.EmployeeServiceImpl;
import com.example.hrm_service.web.EmployeeResponse;
import com.example.hrm_service.web.EmployeeWithPosition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hrm/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeServiceImpl service;

    @PostMapping()
    public ResponseEntity<EmployeeResponse> add(@RequestBody EmployeeDTO employeeDTO){
        return ResponseEntity.ok(service.save(employeeDTO));
    }
    @PostMapping("/addExperience/{id}")
    public ResponseEntity<EmployeeResponse> addExperience(@PathVariable Long id, @RequestBody WorkExperienceDTO workExperienceDTO){
        return ResponseEntity.ok(service.addExperience(id , workExperienceDTO));
    }
    @PostMapping("/addFamily/{id}")
    public ResponseEntity<EmployeeResponse> addFamily(@PathVariable Long id, @RequestBody FamilyDTO familyDTO){
        return ResponseEntity.ok(service.addFamily(id , familyDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeWithPosition>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

}
