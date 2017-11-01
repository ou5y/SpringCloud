package com.azcx9d.agency.exception;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }

    public LoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
