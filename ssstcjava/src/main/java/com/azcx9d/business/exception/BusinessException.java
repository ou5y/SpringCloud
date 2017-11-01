package com.azcx9d.business.exception;

/**
 * Created by fangbaoyan on 2017/5/9.
 */
public class BusinessException extends RuntimeException{

    private Integer code;

    private Object data;

    public BusinessException(Integer code, String message) {
        super(message);
        this.code=code;
    }

    public BusinessException(Integer code, String message, Object data) {
        super(message);
        this.code=code;
        this.data=data;
    }

    public BusinessException(String message, Throwable cause) {
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
