package com.example.program.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponse<T> {
    private T data;
    private int status;
    private String message;

    public ApiResponse(T data, HttpStatus status, String message) {
        this.data = data;
        this.status = status.value();
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildResponse(T data, HttpStatus status, String message) {
        ApiResponse<T> response = new ApiResponse<>(data, status, message);
        return new ResponseEntity<>(response, status);
    }
}
