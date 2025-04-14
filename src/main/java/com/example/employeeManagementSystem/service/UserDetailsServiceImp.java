package com.example.employeeManagementSystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.employeeManagementSystem.entity.UserAccount;
import com.example.employeeManagementSystem.repositories.UserAccountRepo;
import com.example.employeeManagementSystem.shared.CustomResponseException;
@Service
public class UserDetailsServiceImp implements UserDetailsService {
        @Autowired
    UserAccountRepo userAccountRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserAccount> account = userAccountRepo.findOneByUsername(username);

        if (account.isEmpty()) {
            throw  CustomResponseException.BadCredentials();
        }
        UserAccount user = account.get();



        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
        
    }

}
