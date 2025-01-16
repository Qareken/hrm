package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;
    private String description;
//    @OneToOne
//    @JoinColumn(name = "head_id", referencedColumnName = "id")
//    private Employee head;
//    @OneToOne
//    @JoinColumn(name = "clerk_id", referencedColumnName = "id")
//    private Employee clerk;
//    @OneToMany(mappedBy = "division",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<Employee> employees;
}
