package com.example.employeeManagementSystem.abstracts;

import java.util.List;
import java.util.UUID;

import com.example.employeeManagementSystem.dtos.LeaveRequestCreate;
import com.example.employeeManagementSystem.entity.LeaveRequest;

public interface LeaveRequestService {
    public LeaveRequest createOne(LeaveRequestCreate leaveRequest,UUID employeeId);
    List<LeaveRequest> findAllByEmployee(UUID employeeId);

}
