package com.thoughtworks.parking_lot.Dto;

import java.util.List;

public class StatusResponse {
    private String statusMsg;
    private Integer statusCode;
    private List<TypeValuePairs> typeValuePairs;

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public List<TypeValuePairs> getTypeValuePairs() {
        return typeValuePairs;
    }

    public void setTypeValuePairs(List<TypeValuePairs> typeValuePairs) {
        this.typeValuePairs = typeValuePairs;
    }
}
