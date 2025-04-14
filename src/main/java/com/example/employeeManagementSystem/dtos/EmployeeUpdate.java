package com.example.employeeManagementSystem.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeUpdate(
        @NotNull(message = "First name is required") @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters") 
        String firstName,
        @NotNull(message = "Last  name is required") @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters") 
        String lastName,
        @NotNull(message = "Phone number is required") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid") 
        String phoneNumber,
        @NotNull(message = "position is required") @Size(min = 2, max = 30, message = "position must be between 2 and 30 characters") 
        String position,
        @NotNull(message = "departmentId is required")
        UUID departmentId) { 

}
