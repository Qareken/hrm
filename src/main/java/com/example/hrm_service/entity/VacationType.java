package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VacationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String codeType;
    private String name;
    private String description;
    private boolean isActive = true;
    @OneToMany(mappedBy = "vacationType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Vacation> vacations = new HashSet<>();
    @OneToMany(mappedBy = "vacationType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VacationPolicy> policies = new HashSet<>();
    @OneToMany(mappedBy = "vacationType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VacationBalance> balances = new HashSet<>();
}
