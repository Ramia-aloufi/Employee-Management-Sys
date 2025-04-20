package com.example.employeeManagementSystem.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeManagementSystem.entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,UUID> {
    Optional<Employee> findByAccountCreationToken(String token);

}
