package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "business_trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessTrip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    private LocalDate startDate;

    private LocalDate endDate;

    private String destination;

    private String purpose;

    private String attachmentPath;

    private String status;
}