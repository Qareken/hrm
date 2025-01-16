package com.example.hrm_service.mapper;



import com.example.hrm_service.entity.EmployeeFamily;
import com.example.hrm_service.entity.WorkExperience;
import com.example.hrm_service.entity.ExternalWorkExperience;
import com.example.hrm_service.entity.InternalWorkExperience;

import com.example.hrm_service.entity.Position;
import com.example.hrm_service.entity.Division;
import com.example.hrm_service.entity.Employee;


import com.example.hrm_service.web.EmployeeWithPosition;
import com.example.hrm_service.web.FamilyResponse;
import com.example.hrm_service.web.WorkExperienceResponse;
import com.example.hrm_service.web.EmployeeShortResponse;
import com.example.hrm_service.web.DivisionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ResponseMapper {

    public static final ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);


    public abstract FamilyResponse toFamilyResponse(EmployeeFamily entity);

    public abstract List<FamilyResponse> toFamilyResponseList(List<EmployeeFamily> entities);


    public abstract WorkExperienceResponse baseWorkExperienceToResponse(WorkExperience entity);


    @Named("toWorkExperienceResponse")
    public WorkExperienceResponse toWorkExperienceResponse(WorkExperience entity) {
        if (entity == null) {
            return null;
        }
        // сначала маппим общие поля
        WorkExperienceResponse response = baseWorkExperienceToResponse(entity);

        if (entity instanceof ExternalWorkExperience) {
            ExternalWorkExperience ext = (ExternalWorkExperience) entity;
            response.setOrganizationName(ext.getExternalOrganizationName());
            response.setPosition(ext.getExternalPosition());
        } else if (entity instanceof InternalWorkExperience) {
            InternalWorkExperience in = (InternalWorkExperience) entity;
            // Например: organizationName = название Division
            Division division = in.getDivision();
            if (division != null) {
                response.setOrganizationName(division.getName());
            }
            // position = название Position
            Position pos = in.getPosition();
            if (pos != null) {
                response.setPosition(pos.getName());
            }
        }
        return response;
    }


    @IterableMapping(qualifiedByName = "toWorkExperienceResponse")
    public abstract List<WorkExperienceResponse> toWorkExperienceResponseList(Collection<? extends WorkExperience> entities);


    @Named("toEmployeeWithPosition")
    public EmployeeWithPosition toEmployeeWithPosition(Employee emp) {
        if (emp == null) {
            return null;
        }
        // ID
        Long employeeId = emp.getId();
        // Имя (или объединяем first + lastName, как хотите)
        String name = buildFullName(emp);
        // Позиция (например, последняя InternalWorkExperience)
        Position.TypePosition posType = extractCurrentPositionType(emp);

        return new EmployeeWithPosition(employeeId, name, posType);
    }

    protected String buildFullName(Employee emp) {
        StringBuilder sb = new StringBuilder();
        if (emp.getFirstname() != null) sb.append(emp.getFirstname());
        if (emp.getSurname() != null) sb.append(" ").append(emp.getSurname());
        if (emp.getLastName() != null) sb.append(" ").append(emp.getLastName());
        return sb.toString().trim();
    }


    protected Position.TypePosition extractCurrentPositionType(Employee emp) {
        if (emp.getWorkExperiences() == null) return null;

        return emp.getWorkExperiences().stream()
                .filter(we -> we instanceof InternalWorkExperience)
                .map(we -> (InternalWorkExperience) we)
                .sorted(Comparator.comparing(InternalWorkExperience::getStart).reversed())
                .map(in -> in.getPosition() != null ? in.getPosition().getTypePosition() : null)
                .findFirst()
                .orElse(null);
    }

    // Для списков
    @IterableMapping(qualifiedByName = "toEmployeeWithPosition")
    public abstract List<EmployeeWithPosition> toEmployeeWithPositionList(Collection<Employee> employees);

    protected String extractPositionNameFromEmployee(Employee emp) {
        if (emp.getWorkExperiences() == null) return null;
        // ищем актуальную InternalWorkExperience
        return emp.getWorkExperiences().stream()
                .filter(we -> we instanceof InternalWorkExperience)
                .map(we -> (InternalWorkExperience) we)
                .sorted(Comparator.comparing(InternalWorkExperience::getStart).reversed())
                .map(in -> in.getPosition() != null ? in.getPosition().getName() : null)
                .findFirst()
                .orElse(null);
    }

    protected String extractDivisionNameFromEmployee(Employee emp) {
        if (emp.getWorkExperiences() == null) return null;
        // ищем актуальную InternalWorkExperience
        return emp.getWorkExperiences().stream()
                .filter(we -> we instanceof InternalWorkExperience)
                .map(we -> (InternalWorkExperience) we)
                .sorted(Comparator.comparing(InternalWorkExperience::getStart).reversed())
                .map(in -> in.getDivision() != null ? in.getDivision().getName() : null)
                .findFirst()
                .orElse(null);
    }

    public abstract DivisionResponse toDivisionResponse(Division division);


    protected String resolveHead(Division division) {

        return null;
    }

    protected String resolveClerk(Division division) {
        // Аналогично
        return null;
    }


    public abstract List<DivisionResponse> toDivisionResponseList(Collection<Division> divisions);
}
