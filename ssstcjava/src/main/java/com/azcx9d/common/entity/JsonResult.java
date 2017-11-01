package com.azcx9d.common.entity;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class JsonResult<T> {
    //private boolean isSuccess;
    private int status;//0：成功,其他
    private String message;
    private T data;
    
    
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

    public JsonResult(String message, T data) {
		this.message = message;
		this.data = data;
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
