package com.example.hrm_service.repository;

import com.example.hrm_service.entity.InternalWorkExperience;
import com.example.hrm_service.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternalWorkExperienceRepository extends JpaRepository<InternalWorkExperience, Long> {
    @Query("""
        select i 
        from InternalWorkExperience i
        join i.employee e
        join i.division d
        join i.position p
        where e.id = :employeeId
          and d.code = :divisionCode
          and p.typePosition = :typePosition
          and p.grade = :grade
    """)
    Optional<InternalWorkExperience> findByEmployeeDivisionPosition(
            @Param("employeeId") Long employeeId,
            @Param("divisionCode") String divisionCode,
            @Param("typePosition") Position.TypePosition typePosition,
            @Param("grade") Integer grade
    );
    @Query(value = """
    SELECT * 
    FROM internal_work_experience 
    WHERE employee_id = :employeeId
      AND end_date IS NULL
    ORDER BY start DESC
    LIMIT 1
    """,
            nativeQuery = true)
    Optional<InternalWorkExperience> findCurrentWorkExperienceByEmployeeId(@Param("employeeId") Long employeeId);
}

