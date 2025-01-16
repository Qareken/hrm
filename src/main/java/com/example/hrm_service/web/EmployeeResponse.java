package com.example.hrm_service.web;

import com.example.hrm_service.dto.FamilyDTO;
import com.example.hrm_service.dto.WorkExperienceDTO;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeeResponse {
    private String firstname;
    private String surname;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private String numberOfPassport;
    private String address;
    private Set<WorkExperienceDTO> workExperienceDTO;
    private Set<FamilyDTO> familyDTO;
}
