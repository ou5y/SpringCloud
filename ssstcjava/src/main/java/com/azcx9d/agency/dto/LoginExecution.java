package com.azcx9d.agency.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
@ApiModel
public class LoginExecution<T> {


    private  int status;

    private String message;
    
    private T data;
    
    
    



    public LoginExecution(int status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

    
	public LoginExecution(int status, String message) {
		this.status = status;
		this.message = message;
	}


	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
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
