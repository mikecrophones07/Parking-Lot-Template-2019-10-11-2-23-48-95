package com.thoughtworks.parking_lot.Dto;

public class ErrorResponse {

    private Integer statusCode;
    private String message;

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
