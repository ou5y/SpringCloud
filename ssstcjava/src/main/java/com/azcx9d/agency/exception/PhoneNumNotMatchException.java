package com.azcx9d.agency.exception;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class PhoneNumNotMatchException extends RuntimeException{
    public PhoneNumNotMatchException(String message) {
        super(message);
    }

    public PhoneNumNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }
}
