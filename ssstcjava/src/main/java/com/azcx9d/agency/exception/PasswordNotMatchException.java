package com.azcx9d.agency.exception;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class PasswordNotMatchException extends RuntimeException{
    public PasswordNotMatchException(String message) {
        super(message);
    }

    public PasswordNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
