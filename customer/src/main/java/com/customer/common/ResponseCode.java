package com.customer.common;

/**
 * Created by fangbaoyan on 2017/6/1.
 */
public enum ResponseCode {
    SUCCESS(0,"SUCCESS"),
    ERROR(2,"ERROR"),
    NEED_LOGIN(-1,"NEED_LOGIN"),
    NOT_FOUND(4,"NOT_FOUND"),
    ILLEGAL_ARGUMENT(1,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
