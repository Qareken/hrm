package com.example.hrm_service.entity;

import com.example.hrm_service.entity.enumConstants.RelationShip;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Locale;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmployeeFamily {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private LocalDate dateOfBirth;
    private RelationShip relationShip;
    private String address;
    private String work;
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    Employee employee;

}
