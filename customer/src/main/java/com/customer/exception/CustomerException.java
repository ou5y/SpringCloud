package com.customer.exception;

/**
 * Created by fangbaoyan on 2017/5/9.
 */
public class CustomerException extends RuntimeException{

    private Integer code;

    private Object data;

    public CustomerException(Integer code,String message) {
        super(message);
        this.code=code;
    }

    public CustomerException(Integer code,String message,Object data) {
        super(message);
        this.code=code;
        this.data=data;
    }

    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
