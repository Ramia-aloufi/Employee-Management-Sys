 package com.example.employeeManagementSystem.shared;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class GlobalExceptionResponse {

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GlobalResponse<?>> handleNoResourceException(NoResourceFoundException e) {
        var errors = List.of( new GlobalResponse.ErrorItem("Resource not found"));
        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.ACCEPTED);
    }

    @ExceptionHandler(CustomResponseException.class)
    public ResponseEntity<GlobalResponse<?>> handleCustomException(CustomResponseException e) {
        var errors = List.of( new GlobalResponse.ErrorItem(e.getMessage()));
        return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.resolve(e.getCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<?>> handleCustomException(MethodArgumentNotValidException e) {
        var errors = e.getBindingResult().getFieldErrors().stream()
        .map(err -> new GlobalResponse.ErrorItem(err.getField() + " " + err.getDefaultMessage())) 
        .toList();
                return new ResponseEntity<>(new GlobalResponse<>(errors), HttpStatus.ACCEPTED);
    }



}
@Getter
@Setter
@AllArgsConstructor
class Test{
    private String message;        
}
 