package com.customer.enums;

/**
 * Created by fangbaoyan on 2017/5/12.
 */
public enum ResultEnum {
    UNKNOW_ERROR(5,"未知错误"),
    NOT_FOUND(4,"data is null"),
    COMMON_ERROR(2,"message"),
    SUCCESS(0,"ok"),
    TOKEN(-1,"token过期"),
    ;
    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
