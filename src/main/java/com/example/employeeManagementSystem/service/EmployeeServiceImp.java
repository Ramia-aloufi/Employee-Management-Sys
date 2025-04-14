package com.example.employeeManagementSystem.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeManagementSystem.abstracts.EmployeeService;
import com.example.employeeManagementSystem.dtos.EmployeeCreate;
import com.example.employeeManagementSystem.dtos.EmployeeUpdate;
import com.example.employeeManagementSystem.entity.Department;
import com.example.employeeManagementSystem.entity.Employee;
import com.example.employeeManagementSystem.repositories.DepartmentRepo;
import com.example.employeeManagementSystem.repositories.EmployeeRepo;
import com.example.employeeManagementSystem.shared.CustomResponseException;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public Employee findOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("Employee: " + employeeId + " not found"));
        return employee;
    }

    public List<Employee> findAll() {
        return employeeRepo.findAll();
    }

    public void deleteOne(UUID employeeId) {
        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("Employee: " + employeeId + " not found"));
                employeeRepo.delete(employee);
    }

   
    public Employee updateOne(UUID employeeId, EmployeeUpdate employee) {
        Employee existingEmployee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("Employee not found"));
        existingEmployee.setFirstName(employee.firstName());
        existingEmployee.setLastName(employee.lastName());
        existingEmployee.setPhoneNumber(employee.phoneNumber());
        existingEmployee.setPosition(employee.position());
        employeeRepo.save(existingEmployee);
        return existingEmployee;

    }

    public Employee createOne(EmployeeCreate employeeCreate) {
       Department dep =  departmentRepo.findById(employeeCreate.departmentId())
                .orElseThrow(() -> CustomResponseException.ResourceNotFound("Department not found"));
        Employee employee = new Employee(); 
        employee.setFirstName(employeeCreate.firstName());
        employee.setLastName(employeeCreate.lastName());
        employee.setPosition(employeeCreate.position());
        employee.setHireDate(employeeCreate.hireDate());
        employee.setPhoneNumber(employeeCreate.phoneNumber());
        employee.setEmail(employeeCreate.email());
        employee.setDepartment(dep);
        employeeRepo.save(employee);
        return employee;
    }

}
