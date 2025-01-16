package com.example.hrm_service.dto;

import com.example.hrm_service.entity.Position;
import lombok.*;

@Data
@NoArgsConstructor
public class PositionDTO {

    private String name;
    private Integer grade;
    private Position.TypePosition typePosition;
}
