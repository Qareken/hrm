package com.example.hrm_service.repository;

import com.example.hrm_service.entity.Employee;
import com.example.hrm_service.web.EmployeeWithPosition;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    @EntityGraph(attributePaths = {
            "workExperiences",
            "workExperiences.division",
            "workExperiences.position"
    })
    @Query("""
    select e
    from Employee e
    join InternalWorkExperience w on w.employee = e
    where w.division.id = :divisionId
      and w.endTime is null
""")
    List<Employee> findActiveEmployeesByDivisionWithGraph(@Param("divisionId") Long divisionId);
    @Query("""
    select new com.example.hrm_service.web.EmployeeWithPosition(
               e.id,
               e.firstname,
               w.position.typePosition
           )
    from Employee e
    join InternalWorkExperience w on w.employee = e
    where w.division.id = :divisionId
      and w.endTime is null
""")
    List<EmployeeWithPosition> findEmployeesWithPositionByDivision(@Param("divisionId") Long divisionId);

}
