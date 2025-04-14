package com.example.employeeManagementSystem.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeManagementSystem.abstracts.DepartmentService;
import com.example.employeeManagementSystem.dtos.DepartmentCreate;
import com.example.employeeManagementSystem.entity.Department;
import com.example.employeeManagementSystem.shared.GlobalResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/department") // Base URL for the API
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Department>>> findAll() {
        List<Department> departments = departmentService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(departments), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<GlobalResponse<Department>> createOne(@RequestBody @Valid DepartmentCreate departmentCreate) {
        Department department = departmentService.createOne(departmentCreate);
        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.CREATED);
    }
    @GetMapping("/{departmentId}")
    public ResponseEntity<GlobalResponse<Department>> findOne(@PathVariable UUID departmentId) {
        Department department = departmentService.findOne(departmentId);
        return new ResponseEntity<>(new GlobalResponse<>(department), HttpStatus.OK);
    }

}
