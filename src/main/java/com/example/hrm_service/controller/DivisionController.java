package com.example.hrm_service.controller;

import com.example.hrm_service.dto.DivisionDTO;
import com.example.hrm_service.service.impl.DivisionServiceImpl;
import com.example.hrm_service.web.DivisionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/hrm/division")
@RequiredArgsConstructor
public class DivisionController {
    private final DivisionServiceImpl divisionService;
    @GetMapping
    public ResponseEntity<DivisionResponse> findAll(){
        return null;
    }
    @PostMapping()
    public ResponseEntity<DivisionResponse> add(@RequestBody DivisionDTO employeeDTO){
        return ResponseEntity.ok(divisionService.save(employeeDTO));
    }
    @PostMapping("/update")
    public ResponseEntity<DivisionResponse> addExperience(@PathVariable Long id, @RequestBody DivisionDTO divisionDTO){
        return ResponseEntity.ok(divisionService.update( divisionDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<DivisionResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(divisionService.findById(id));
    }
}
