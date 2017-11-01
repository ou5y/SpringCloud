package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/4/21 0021.
 */

@ApiModel(value = "系统公告")
public class NoticeDto {

    @ApiModelProperty(value = "主键id")
    private int id;

    @ApiModelProperty(value = "是否开启")
    private int isOpen;

    @ApiModelProperty(value = "消息类型：0：系统公告    1：安全公告")
    private int type;

    @ApiModelProperty(value = "消息内容")
    private String message;

    @ApiModelProperty(value = "APP类型：1：Android商户端 2 :Android代理端 3：Android用户端 4：IOS商户端 5：iOS代理端 6：IOS用户端")
    private int appType;

    @ApiModelProperty(value = "创建时间")
    private Date creatTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }
}
