
package com.example.ApiResponse;

import java.util.HashMap;
import java.util.Map;

public class ApiResponce {
    private String massage;
    private Map<String, Object> data = new HashMap<>();
    private int statusCode;

    public ApiResponce() {
    }

    public ApiResponce(String massage, Map<String, Object> data, int statusCode) {
        this.massage = massage;
        this.data = data;
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }
}
