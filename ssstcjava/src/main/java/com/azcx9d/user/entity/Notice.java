package com.azcx9d.user.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/4/21 0021.
 */

@ApiModel(value = "系统公告")
public class Notice {

    @ApiModelProperty(value = "主键id")
    private long id;

    @ApiModelProperty(value = "是否开启")
    private int isOpen;

    @ApiModelProperty(value = "消息类型：0：系统公告    1：安全公告")
    private int type;

    @ApiModelProperty(value = "消息内容")
    private String message;

    @ApiModelProperty(value = "APP类型：1：Android商户端 2 :Android代理端3：Android用户端 4：IOS商户端 5：iOS代理端 6：IOS用户端")
    private int appType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(int isOpen) {
        this.isOpen = isOpen;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }

}
