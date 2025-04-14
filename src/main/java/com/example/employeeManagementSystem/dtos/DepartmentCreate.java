package com.example.employeeManagementSystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DepartmentCreate(
    @NotNull(message = "name is required") @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
    String name
)  {

}
