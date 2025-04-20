package com.example.employeeManagementSystem.entity;

import java.time.LocalDate;
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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity 
@Table(name = "employee ")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @UuidGenerator
private UUID id;
@Column(name = "first_name",nullable = false,length = 100)
private String firstName;
@Column(name = "last_name",nullable = false,length = 100)
private String lastName;
@Column(name = "email",nullable = false,unique = true)
private String email;
@Column(name = "phone_number",length = 25)
private String phoneNumber;
@Column(name = "hire_date",nullable = false)
private LocalDate hireDate;
@Column(name = "position",length = 100)
private String position;
@Column(name = "account_creation_token")
private String accountCreationToken;
@Column(name = "is_verified", columnDefinition = "boolean default false",nullable = false)
private boolean isVerified;
@ManyToOne(fetch = FetchType.LAZY,optional = false)
@JoinColumn(name = "department_id" ,nullable = false )
@JsonProperty("department_id")
private Department department ;
public UUID getDepartment() { 
    return department.getId();
}
}
