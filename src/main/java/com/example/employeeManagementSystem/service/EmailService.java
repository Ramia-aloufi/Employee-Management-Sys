package com.example.employeeManagementSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    @Autowired
    private JavaMailSender mailSender;

     @Value("${backend.origin}")
     private String ORIGIN;
     @Value("${spring.mail.username}")
     private String HOST;

    public void sendEmail(String to, String token) {
         String link = ORIGIN + "/employees/signup?token=" + token;
         String subject = "Account Verification";
         String body = "Please click the link below to verify your account:\n" + link;
         SimpleMailMessage message = new SimpleMailMessage();
         message.setFrom(HOST);
         message.setTo(to);
         message.setSubject(subject);
         message.setText(body);
         mailSender.send(message);
    }

}
