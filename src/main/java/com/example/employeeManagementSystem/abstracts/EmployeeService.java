package com.example.employeeManagementSystem.abstracts;

import java.util.List;
import java.util.UUID;

import com.example.employeeManagementSystem.dtos.EmployeeCreate;
import com.example.employeeManagementSystem.dtos.EmployeeUpdate;
import com.example.employeeManagementSystem.entity.Employee;

public interface EmployeeService {
    Employee findOne(UUID employeeId);
    List<Employee> findAll();
    void deleteOne(UUID employeeId);
    Employee updateOne(UUID employeeId, EmployeeUpdate employee);
    Employee createOne(EmployeeCreate employee);


}
