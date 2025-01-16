package com.example.hrm_service.dto;


import com.example.hrm_service.entity.WorkExperience;
import lombok.*;


import java.time.LocalDate;
import java.util.Set;
@Data
@NoArgsConstructor
public class EmployeeDTO {

    private String firstname;
    private String surname;
    private String lastName;
    private LocalDate dateOfBirth;
    private String phoneNumber;
    private String email;
    private String numberOfPassport;
    private String address;

    Set<FamilyDTO> familyMember;
    Set<WorkExperience> workExperiences;

}
