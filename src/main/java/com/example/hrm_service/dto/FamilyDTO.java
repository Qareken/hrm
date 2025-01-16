package com.example.hrm_service.dto;

import com.example.hrm_service.entity.enumConstants.RelationShip;
import lombok.*;

import java.time.LocalDate;
@Data
@NoArgsConstructor
public class FamilyDTO {

    private String name;
    private String lastname;
    private String surname;
    private LocalDate dateOfBirth;
    private RelationShip relationShip;
    private String address;
    private String work;
}
