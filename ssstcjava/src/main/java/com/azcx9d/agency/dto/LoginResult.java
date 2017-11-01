package com.azcx9d.agency.dto;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class LoginResult {

    private int status;

    private String message;

    public LoginResult(int status, String message) {
        this.status = status;
        this.message = message;
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
