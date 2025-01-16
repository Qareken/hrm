package com.example.hrm_service.web;

import lombok.*;

@Data
@NoArgsConstructor
public class EmployeeShortResponse {
    private String firstname;
    private String surname;
    private String lastName;
    private String position;
    private String division;
}
