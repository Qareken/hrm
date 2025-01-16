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

/**
 * Пример маппера, который преобразует сущности
 * в различные Response-объекты.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ResponseMapper {

    public static final ResponseMapper INSTANCE = Mappers.getMapper(ResponseMapper.class);

    // --------------------------------------------------------------------------------
    // 1. FamilyResponse
    // --------------------------------------------------------------------------------
//    @Mapping(target = "id", source = "id")
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "lastname", source = "lastname")
//    @Mapping(target = "surname", source = "surname")
//    @Mapping(target = "dateOfBirth", source = "dateOfBirth")
//    @Mapping(target = "relationShip", source = "relationShip")
//    @Mapping(target = "address", source = "address")
//    @Mapping(target = "work", source = "work")
    public abstract FamilyResponse toFamilyResponse(EmployeeFamily entity);

    public abstract List<FamilyResponse> toFamilyResponseList(List<EmployeeFamily> entities);

    // --------------------------------------------------------------------------------
    // 2. WorkExperienceResponse
    //
    // У вас в WorkExperienceResponse есть поля:
    //  - start
    //  - end
    //  - notes
    //  - organizationName
    //  - position
    //
    // Для ExternalWorkExperience: organizationName = externalOrganizationName, position = externalPosition
    // Для InternalWorkExperience: organizationName = division.name, position = position.name (или typePosition.name)
    // --------------------------------------------------------------------------------

//    @Mapping(target = "start", source = "start")
//    @Mapping(target = "end", source = "end")
//    @Mapping(target = "notes", source = "notes")
//    // organizationName и position будут проставляться кастомно:
//    @Mapping(target = "organizationName", ignore = true)
//    @Mapping(target = "position", ignore = true)
    public abstract WorkExperienceResponse baseWorkExperienceToResponse(WorkExperience entity);

    /**
     * Универсальный метод, который проверяет наследника
     * (ExternalWorkExperience / InternalWorkExperience)
     * и маппит в {@link WorkExperienceResponse}
     */
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

    /**
     * Для списков
     */
    @IterableMapping(qualifiedByName = "toWorkExperienceResponse")
    public abstract List<WorkExperienceResponse> toWorkExperienceResponseList(Collection<? extends WorkExperience> entities);

    // --------------------------------------------------------------------------------
    // 3. EmployeeWithPosition
    //    Обычно это "облегчённая" модель, где нужно ID, ФИО и актуальная должность.
    // --------------------------------------------------------------------------------
    /**
     * Пример маппинга для "короткой" информации о сотруднике с позицией
     * Возможно, вы получаете эти данные через кастомный запрос (JOIN),
     * но если нужно собрать вручную — вот пример.
     */
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

    /**
     * Вспомогательный метод для сборки ФИО.
     * Зависит от ваших нужд: можно собрать строкой, можно по-разному.
     */
    protected String buildFullName(Employee emp) {
        StringBuilder sb = new StringBuilder();
        if (emp.getFirstname() != null) sb.append(emp.getFirstname());
        if (emp.getSurname() != null) sb.append(" ").append(emp.getSurname());
        if (emp.getLastName() != null) sb.append(" ").append(emp.getLastName());
        return sb.toString().trim();
    }

    /**
     * Вспомогательный метод, чтобы вытащить тип позиции (TypePosition)
     * из актуального (или последнего) InternalWorkExperience.
     * Логика — примерная, настройте под себя.
     */
    protected Position.TypePosition extractCurrentPositionType(Employee emp) {
        if (emp.getWorkExperiences() == null) return null;
        // Ищем последнюю InternalWorkExperience
        // Можно по startDate, например
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

    // --------------------------------------------------------------------------------
    // 4. EmployeeShortResponse
    //    (firstname, surname, lastName, position, division)
    //
    //    Предположим, position и division также берём из актуального InternalWorkExperience.
    // --------------------------------------------------------------------------------
//    @Mapping(target = "firstname", source = "firstname")
//    @Mapping(target = "surname", source = "surname")
//    @Mapping(target = "lastName", source = "lastName")
    // position и division берём кастомно (через выражения или default-методы)
//    @Mapping(target = "position", expression = "java(extractPositionNameFromEmployee(entity))")
//    @Mapping(target = "division", expression = "java(extractDivisionNameFromEmployee(entity))")
//    public abstract EmployeeShortResponse toEmployeeShortResponse(Employee entity);

    // Для списков
//    public abstract List<EmployeeShortResponse> toEmployeeShortResponseList(Collection<Employee> entities);

    /**
     * Можно прописать логику, как извлечь название позиции (string)
     */
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

    // --------------------------------------------------------------------------------
    // 5. DivisionResponse
    //    Имеет поля: name, description, employeeResponses (List<EmployeeWithPosition>), head, clerk
    //    Сейчас head и clerk — просто String, но в сущности может быть Employee,
    //    или они могут быть закомментированы.
    // --------------------------------------------------------------------------------
//    @Mapping(target = "name", source = "name")
//    @Mapping(target = "description", source = "description")
    // employees -> employeeResponses
    // head -> вычислять кастомно
    // clerk -> вычислять кастомно
//    @Mapping(target = "employeeResponses", expression = "java(toEmployeeWithPositionList(entityEmployeesOrEmpty(division)))")
//    @Mapping(target = "head", expression = "java(resolveHead(division))")
//    @Mapping(target = "clerk", expression = "java(resolveClerk(division))")
    public abstract DivisionResponse toDivisionResponse(Division division);

    /**
     * Если поля head, clerk в `Division` закомментированы, значит данные где-то ещё.
     * Здесь можем вернуть null или какую-то заглушку.
     */
    protected String resolveHead(Division division) {
        // Пример: если бы была связь @OneToOne Employee head
        // return division.getHead() != null ? division.getHead().getFirstname() : null;
        return null;
    }

    protected String resolveClerk(Division division) {
        // Аналогично
        return null;
    }

    /**
     * Пример получения списка сотрудников из division.getEmployees().
     */
//    protected Set<Employee> entityEmployeesOrEmpty(Division division) {
//        // Если getEmployees() == null, вернём пустой сет
//        return division.getE() != null ? division.getEmployees() : Collections.emptySet();
//    }

    // Если нужно делать список DivisionResponse:
    public abstract List<DivisionResponse> toDivisionResponseList(Collection<Division> divisions);
}
