package com.camila.cictema.util;

public class CustomResponse<T> {
    private T data;
    private String message;
    private boolean success;
    private int status;

    // Constructor
    public CustomResponse(T data, Boolean success, String message, int status) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.success = success;
    }

    // Getters y Setters
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
