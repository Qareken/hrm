package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vacation_balance")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "vacation_type_id", nullable = false)
    private VacationType vacationType;

    private Integer year;

    private Integer accruedDays;

    private Integer usedDays;
    private Integer usedCount;
}
