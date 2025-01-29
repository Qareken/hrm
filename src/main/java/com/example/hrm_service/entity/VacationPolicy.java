package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacationPolicy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vacation_type_id", nullable = false, referencedColumnName = "id")
    private VacationType vacationType;

    private Integer defaultDays;

    private Boolean workingDays;

    private Boolean canSplit;

    private Integer maxSplits;

    private Integer daysPerFiveYears;

    private Boolean unpaid;

    private Boolean attestation;
    private Position.TypePosition typePosition;
}
