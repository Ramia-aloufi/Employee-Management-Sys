package com.example.employeeManagementSystem.utils;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.employeeManagementSystem.repositories.UserAccountRepo;

@Component
public class SecurityUtils {
    @Autowired
    private UserAccountRepo userAccountRepo;

    public boolean isOwner(UUID employeeId) {
        // 1- Get authenticated user
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 2- Get authenticated user details
        final UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println("SecurityUtils: " + userDetails);
        // 3- Check if it's the same user
        return userAccountRepo.isOwner(userDetails.getUsername(), employeeId);
    }

}
