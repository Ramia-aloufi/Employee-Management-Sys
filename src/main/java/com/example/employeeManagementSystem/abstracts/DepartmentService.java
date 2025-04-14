package com.example.employeeManagementSystem.abstracts;

import java.util.List;
import java.util.UUID;

import com.example.employeeManagementSystem.dtos.DepartmentCreate;
import com.example.employeeManagementSystem.entity.Department;

public interface DepartmentService {
    Department findOne(UUID departmentId);
    List<Department> findAll();
    void deleteOne(UUID departmentId);
    Department createOne(DepartmentCreate departmentCreate);

}
