package com.example.employeeManagementSystem.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

public record LeaveRequestCreate(
    @Column(name = "start_date", nullable = false)
     String startDate,
    @NotNull(message = "end_date is required")
    @Column(name = "end_date", nullable = false)
     String endDate,
    @NotNull(message = "reason is required")
    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
     String reason
){

}
