package com.example.hrm_service.entity;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("INTERNAL")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InternalWorkExperience extends WorkExperience{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
    @ManyToOne
    @JoinColumn(name = "division_id", referencedColumnName = "id")
    private Division division;
    @ManyToOne
    @JoinColumn(name = "postion_id", referencedColumnName = "id")
    private Position position;
}
