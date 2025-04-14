package com.example.employeeManagementSystem.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeManagementSystem.entity.LeaveRequest;
@Repository
public interface LeaveRequestRepo extends JpaRepository<LeaveRequest, UUID> {
     
    List<LeaveRequest> findAllByEmployeeId(UUID employeeId);
}
