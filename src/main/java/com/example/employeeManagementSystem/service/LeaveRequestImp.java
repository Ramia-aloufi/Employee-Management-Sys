package com.example.employeeManagementSystem.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.employeeManagementSystem.abstracts.LeaveRequestService;
import com.example.employeeManagementSystem.dtos.LeaveRequestCreate;
import com.example.employeeManagementSystem.entity.Employee;
import com.example.employeeManagementSystem.entity.LeaveRequest;
import com.example.employeeManagementSystem.repositories.EmployeeRepo;
import com.example.employeeManagementSystem.repositories.LeaveRequestRepo;
import com.example.employeeManagementSystem.shared.CustomResponseException;
@Service
public class LeaveRequestImp implements LeaveRequestService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private LeaveRequestRepo leaveRequestRepo;

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")

    public LeaveRequest createOne(LeaveRequestCreate leaveRequest,UUID employeeId) {
        Employee emp = employeeRepo.findById(employeeId)
        .orElseThrow(() -> CustomResponseException.ResourceNotFound("Employee not found"));
        LeaveRequest leaveRequestEntity = new LeaveRequest();
        leaveRequestEntity.setStartDate(leaveRequest.startDate());
        leaveRequestEntity.setEndDate(leaveRequest.endDate());
        leaveRequestEntity.setStatus("Pending");    
        leaveRequestEntity.setReason(leaveRequest.reason());
        leaveRequestEntity.setEmployee(emp); 
        leaveRequestRepo.save(leaveRequestEntity);  
        return leaveRequestEntity;
    }

    @PreAuthorize("@securityUtils.isOwner(#employeeId)")
    public List<LeaveRequest> findAllByEmployee(UUID employeeId) {
        List<LeaveRequest> leaveRequests = leaveRequestRepo.findAllByEmployeeId(employeeId);
        return leaveRequests;
        
    } 

}
