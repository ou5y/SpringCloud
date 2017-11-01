package com.customer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by fangbaoyan on 2017/5/14.
 */
@ApiModel(value = "我的收益")
public class CIncomeRecodEntity {

    @ApiModelProperty(value = "金额")
    private String money;

    @ApiModelProperty(value = "时间")
    private Date dateTime;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
