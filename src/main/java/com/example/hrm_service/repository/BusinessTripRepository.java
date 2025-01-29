package com.example.hrm_service.repository;

import com.example.hrm_service.entity.BusinessTrip;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusinessTripRepository extends JpaRepository<BusinessTrip, Long> {
}
