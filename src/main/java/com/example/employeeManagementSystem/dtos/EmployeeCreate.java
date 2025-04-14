package com.example.employeeManagementSystem.dtos;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeCreate(
        @NotNull(message = "First name is required") @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
         String firstName,
        @NotNull(message = "Last  name is required") @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
         String lastName,
        @NotNull(message = "Email is required") @Email(message = "Email should be valid")
         String email,
        @NotNull(message = "Phone number is required") @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Phone number should be valid")
         String phoneNumber,
        @NotNull(message = "hireDate is required") @PastOrPresent(message = "hireDate should be in the past or present")
        LocalDate hireDate,
        String position,
        @NotNull(message = "Department ID is required")
        UUID departmentId
        ) {

}
