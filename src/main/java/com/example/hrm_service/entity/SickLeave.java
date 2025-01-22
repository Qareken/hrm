package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "sick_leaves")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SickLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;


    private LocalDate startDate;


    private LocalDate endDate;


    private String attachmentPath;

    private String status;
}
