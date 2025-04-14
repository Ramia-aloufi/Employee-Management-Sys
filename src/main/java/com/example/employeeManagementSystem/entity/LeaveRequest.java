package com.example.employeeManagementSystem.entity;

import java.util.UUID;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "leave_request")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequest {

    @UuidGenerator
    @Id
    private UUID id;
     @Column(name = "start_date", nullable = false)
    private String startDate;
    @NotNull(message = "end_date is required")
    @Column(name = "end_date", nullable = false)
    private String endDate;
    @NotNull(message = "reason is required")
    @Column(name = "reason", nullable = false, columnDefinition = "TEXT")
    private String reason;
    @NotNull(message = "status is required")
    @Column(name = "status", nullable = false, length = 20)
    private String status;
    @NotNull(message = "employee_id is required")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonProperty("employee_id")
    private Employee employee;
    public UUID getEmployee() { 
        return employee.getId();
    }
}
