package com.example.employeeManagementSystem.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.employeeManagementSystem.entity.Department;
@Repository
public interface DepartmentRepo extends JpaRepository<Department, UUID> {


}
