package com.example.hrm_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer grade;
    private TypePosition typePosition;
    public enum TypePosition {
        DEPUTY("deputy"), DIRECTOR("director"), HEAD("head"), ENGINEER("engineer"), LEAD_ENGINEER("lead engineer"), TECHNICIAN("technician"),CLERK("clerk");
        private String label;

        TypePosition(String label) {
            this.label = label;
        }
    }
}
