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
import com.example.employeeManagementSystem.shared.CustomResponseException;

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

    public void signUp(SignupRequest signupRequest ,String token) {
        // Check if the username already exists
        Employee emp = employeeRepo.findByAccountCreationToken(token)
                .orElseThrow(
                        () -> CustomResponseException.ResourceNotFound("Invalid token"));
        if(emp.isVerified()){
            throw CustomResponseException.BadRequest("Account already verified");
        }
        // Create a new UserAccount
        UserAccount account = new UserAccount();
        account.setUsername(signupRequest.username());
        account.setPassword(passwordEncoder.encode(signupRequest.password()));
        account.setEmployee(emp);
        // Save the UserAccount to the database
        userAccountRepo.save(account);
        // Update the Employee entity
        emp.setVerified(true);
        emp.setAccountCreationToken(null);
        employeeRepo.save(emp);
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
