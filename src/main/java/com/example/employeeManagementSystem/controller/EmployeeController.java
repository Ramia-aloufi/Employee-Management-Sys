package com.example.employeeManagementSystem.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeManagementSystem.abstracts.EmployeeService;
import com.example.employeeManagementSystem.abstracts.LeaveRequestService;
import com.example.employeeManagementSystem.dtos.EmployeeCreate;
import com.example.employeeManagementSystem.dtos.EmployeeUpdate;
import com.example.employeeManagementSystem.dtos.LeaveRequestCreate;
import com.example.employeeManagementSystem.entity.Employee;
import com.example.employeeManagementSystem.entity.LeaveRequest;
import com.example.employeeManagementSystem.shared.GlobalResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee") // Base URL for the API
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private LeaveRequestService leaveRequestService;
    


    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping
    public ResponseEntity<GlobalResponse<List<Employee>>> findAll() {
        List<Employee> employees = employeeService.findAll();
        return new ResponseEntity<>(new GlobalResponse<>(employees), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> findOne(@PathVariable UUID employeeId) {
        Employee employee =  employeeService.findOne(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(employee), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteOne(@PathVariable UUID employeeId) {
employeeService.deleteOne(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<GlobalResponse<Employee>> updateOne(@PathVariable UUID employeeId, @RequestBody @Valid EmployeeUpdate employee) {
        Employee existingEmployee = employeeService.updateOne(employeeId, employee);
        return new ResponseEntity<>(new GlobalResponse<>(existingEmployee), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GlobalResponse<Employee>> createOne(@RequestBody @Valid  EmployeeCreate employee) {
        System.out.println(employee);
       Employee em =  employeeService.createOne(employee);
        return new ResponseEntity<>(new GlobalResponse<>(em), HttpStatus.CREATED);
    }

    @PostMapping("/{employeeId}/leave-request")
    public ResponseEntity<GlobalResponse<LeaveRequest>> createLeaveRequest(@PathVariable UUID employeeId, @RequestBody @Valid LeaveRequestCreate leaveRequest) {
        LeaveRequest leave = leaveRequestService.createOne(leaveRequest, employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(leave), HttpStatus.CREATED);
    }

    @GetMapping("/{employeeId}/leave-requests")
    public ResponseEntity<GlobalResponse<List<LeaveRequest>>> getAllLeaveRequests(@PathVariable UUID employeeId) {
        System.out.println("employeeId: " +  employeeId);
        List<LeaveRequest> leave = leaveRequestService.findAllByEmployee(employeeId);
        return new ResponseEntity<>(new GlobalResponse<>(leave), HttpStatus.CREATED);
    }
} 