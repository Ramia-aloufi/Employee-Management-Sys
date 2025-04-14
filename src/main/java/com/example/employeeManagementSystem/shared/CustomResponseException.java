package com.example.employeeManagementSystem.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomResponseException extends RuntimeException {
    private int code;
    private String message;


    public static CustomResponseException ResourceNotFound(String message){
        return new CustomResponseException(404,message);
    }

    public static CustomResponseException BadCredentials(){
        return new CustomResponseException(401,"Bad credentials");
    }

}
 