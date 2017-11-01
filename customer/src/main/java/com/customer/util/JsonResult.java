package com.customer.util;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class JsonResult<T> {

    private int status;//-1:token失效,0：成功
    private String message;
    private T data;

    public JsonResult(){

    }
    
    public JsonResult(T data) {
        this(0, "OK", data);
    }

    public JsonResult(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public JsonResult(int status, String message) {
    	this.status = status;
        this.message = message;
    }



    public JsonResult(int status) {
        this.status = status;
    }

    public int getstatus() {
        return status;
    }

    public void setstatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
