package com.customer.exception;

/**
 * Created by HuangQing on 2017/5/12 0012 16:28.
 */
public class FileUpException extends RuntimeException {

    public FileUpException(){
        super();
    }

    public FileUpException(String message){
        super(message);
    }

}
