package org.example.student.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class CreatValidationRespDto {
    private LocalDateTime timestamp;
    private int StatusCode;
    private String error;
    private String message;
    private String path;
    private Map<String,String> fieldErrors;

    public CreatValidationRespDto(LocalDateTime timestamp, int statusCode, String error, String message, String path, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        StatusCode = statusCode;
        this.error = error;
        this.message = message;
        this.path = path;
        this.fieldErrors = fieldErrors;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
