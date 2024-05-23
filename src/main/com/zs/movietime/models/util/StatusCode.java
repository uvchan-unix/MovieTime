package com.zs.movietime.models.util;

import java.util.HashMap;
import java.util.Map;

public class StatusCode {

    private String status; 
    private Object obj;

    public StatusCode(String status, Object obj) {
        this.status = status;
        this.obj = obj;
    }

    public StatusCode(String code) {
        this.status = code;
    }

    public static final Map<String, String> statusMap = new HashMap<>() {{
        put("0", "Unknown status");
        put("222", "Success :)");
        put("111", "Failed :(");

        put("200", "Login Successful");
        put("100", "Login Credentials Failed");

        put("201", "Object retrival successfull");
        put("101", "Object retrival failed");

        put(null, null);
    }};

    public Object getObject() {
        return this.obj;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setObject(Object obj) {
        this.obj = obj;
    }

    public boolean isSuccess(){
        return status.startsWith("2") ? true : false;
    }

    @Override
    public String toString() {
        String message = statusMap.getOrDefault(status, "Unknown status code");
        return "status: {" + status + " " + message +"}"+ (obj != null ? ", Data: " + obj.toString() : "");
    }

    public int toInt() {
        try {
            return Integer.parseInt(status);
        } catch (NumberFormatException e) {
            return -1; 
        }
    }
}
