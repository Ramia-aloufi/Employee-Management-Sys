package com.example.employeeManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeManagementSystem.dtos.LoginRequest;
import com.example.employeeManagementSystem.dtos.SignupRequest;
import com.example.employeeManagementSystem.service.AuthService;
import com.example.employeeManagementSystem.shared.GlobalResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;



    @PostMapping("/SignUp")
    public ResponseEntity<GlobalResponse<SignupRequest>> signUp(@RequestBody @Valid SignupRequest signupRequest) {
        authService.signUp(signupRequest);
        return new ResponseEntity<>(new GlobalResponse<>(signupRequest), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<GlobalResponse<String>> login(@RequestBody @Valid LoginRequest loginRequest) {
        String token = authService.login(loginRequest);
        return new ResponseEntity<>(new GlobalResponse<>(token), HttpStatus.OK);
    }

}
