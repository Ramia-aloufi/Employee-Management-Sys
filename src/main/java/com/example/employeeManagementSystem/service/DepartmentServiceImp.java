package com.example.employeeManagementSystem.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.employeeManagementSystem.abstracts.DepartmentService;
import com.example.employeeManagementSystem.dtos.DepartmentCreate;
import com.example.employeeManagementSystem.entity.Department;
import com.example.employeeManagementSystem.repositories.DepartmentRepo;
@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepo departmentRepo;

   
    public Department findOne(UUID employeeId) {
        Department department = departmentRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Department: " + employeeId + " not found"));
        return department;
           }

    @Override
    public List<Department> findAll() {
        return departmentRepo.findAll();
    }

    
    public void deleteOne(UUID employeeId) {
        Department department = departmentRepo.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Department: " + employeeId + " not found"));
        departmentRepo.delete(department);        
    }

   
    public Department createOne(DepartmentCreate departmentCreate) {
        Department department = new Department(); 
        department.setName(departmentCreate.name());
        departmentRepo.save(department);
        return department;
    }

}
