package com.azcx9d.business.enums;

/**
 * Created by fangbaoyan on 2017/5/12.
 */
public enum QuataEnum {
    DEFAULT_QUOTA(0,"低额"),
    MIN(1,"小额"),
    MID(2,"中额"),
    BIG(3,"大额"),
    ;
    private Integer code;

    private String quataDesc;
    QuataEnum(Integer code, String quataDesc) {
        this.code = code;
        this.quataDesc = quataDesc;
    }

    public Integer getCode() {
        return code;
    }

    public String getQuataDesc() {
        return quataDesc;
    }

}
