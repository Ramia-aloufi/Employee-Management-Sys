package com.example.employeeManagementSystem.shared;

import java.util.List;

import lombok.Getter;
@Getter
public class GlobalResponse<T> {
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";


    private final String status;
    private final T data;
    private final List<ErrorItem> errors;


    public GlobalResponse(T data) {
        this.status = SUCCESS;
        this.data = data;
        this.errors = null;
    }
    public GlobalResponse(List<ErrorItem> errors) {
        this.status = ERROR;
        this.data = null;
        this.errors = errors;
    }
    public record ErrorItem(String message) {
    }

}
