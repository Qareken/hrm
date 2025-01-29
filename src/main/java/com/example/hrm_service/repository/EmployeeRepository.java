package com.example.hrm_service.repository;

import com.example.hrm_service.entity.Employee;
import com.example.hrm_service.web.EmployeeWithPosition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee , Long> {
    @EntityGraph(attributePaths = {"vacations", "balances"})
    @Query("""
        SELECT e
        FROM Employee e
        JOIN InternalWorkExperience we on we.employee = e
        JOIN we.division d
        WHERE d.id = :divisionId
          AND we.endTime IS NULL
        """)
    Page<Employee> findByDivisionIdWithVacationsAndBalances(@Param("divisionId") Long divisionId,
                                                            Pageable pageable);
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
    @EntityGraph(attributePaths = {"vacations"})
    Optional<Employee> findByIdWithVacations(Long id);
}
