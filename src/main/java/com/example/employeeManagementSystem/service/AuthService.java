package com.example.employeeManagementSystem.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.employeeManagementSystem.config.JwtHelper;
import com.example.employeeManagementSystem.dtos.LoginRequest;
import com.example.employeeManagementSystem.dtos.SignupRequest;
import com.example.employeeManagementSystem.entity.Employee;
import com.example.employeeManagementSystem.entity.UserAccount;
import com.example.employeeManagementSystem.repositories.EmployeeRepo;
import com.example.employeeManagementSystem.repositories.UserAccountRepo;

@Service
public class AuthService {

    @Autowired
    UserAccountRepo userAccountRepo;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtHelper jwtHelper;

    public void signUp(SignupRequest signupRequest) {
        // Check if the username already exists
        System.out.println("PASS: " + passwordEncoder.encode(signupRequest.password()));

        Employee emp = employeeRepo.findById(signupRequest.employeeId())
                .orElseThrow(
                        () -> new RuntimeException("Employee with id :" + signupRequest.employeeId() + " not found"));
        UserAccount account = new UserAccount();
        account.setUsername(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(emp);
        System.out.println("AuthService: " + account);
        userAccountRepo.save(account);
    }

    public String login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(), loginRequest.password()));
                        Map<String, Object> customClaims = new HashMap<>();

                        UserAccount account = userAccountRepo.findOneByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("User not found"));
                customClaims.put("userId", account.getId());

                

        return jwtHelper.generateToken(customClaims,account);
    }

}
