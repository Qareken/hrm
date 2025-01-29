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
    private Long id;
    private String codeType;
    private String name;
    private String description;
    @Column(nullable = false, columnDefinition = "BOOLEAN DEFAULT true")
    private boolean isActive;
    @OneToMany(mappedBy = "vacationType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Vacation> vacations ;
    @OneToMany(mappedBy = "vacationType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VacationPolicy> policies ;
    @OneToMany(mappedBy = "vacationType", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<VacationBalance> balances ;
}
