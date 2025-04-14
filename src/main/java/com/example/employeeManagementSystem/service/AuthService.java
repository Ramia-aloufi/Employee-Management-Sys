package com.example.employeeManagementSystem.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employeeManagementSystem.dtos.LoginRequest;
import com.example.employeeManagementSystem.dtos.SignupRequest;
import com.example.employeeManagementSystem.entity.Employee;
import com.example.employeeManagementSystem.entity.UserAccount;
import com.example.employeeManagementSystem.repositories.EmployeeRepo;
import com.example.employeeManagementSystem.repositories.UserAccountRepo;

import jakarta.validation.Valid;

@Service
public class AuthService  {

    @Autowired
    UserAccountRepo userAccountRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void signUp(SignupRequest signupRequest) {
        // Check if the username already exists
        Employee emp = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(() -> new RuntimeException("Employee with id :"+ signupRequest.employeeId() +" not found"));
        UserAccount account = new UserAccount();
        account.setUsername(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(emp);
        userAccountRepo.save(account);
    }

    public String login(LoginRequest loginRequest) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

   


}
