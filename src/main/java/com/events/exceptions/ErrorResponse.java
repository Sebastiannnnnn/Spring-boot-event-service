package com.events.exceptions;

import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ErrorResponse {
    private Date timestamp;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorResponse(Date timestamp, HttpStatus status, String message, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(Date timestamp, HttpStatus status, String message, String error) {
        super();
        this.timestamp = timestamp;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}