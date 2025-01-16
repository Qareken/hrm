package com.example.hrm_service.web;

import com.example.hrm_service.entity.Position;
import lombok.*;

@Data
@NoArgsConstructor
public class EmployeeWithPosition {

    private Long employeeId;
    private String name;
    private Position.TypePosition position;

    public EmployeeWithPosition(Long employeeId, String name, Position.TypePosition position) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
    }
}
