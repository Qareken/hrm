package com.example.hrm_service.web;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
public class DivisionResponse {
    private String name;
    private String description;
    private String code;
    private List<EmployeeWithPosition> employeeResponses;
    private String head;
    private String clerk;
}
