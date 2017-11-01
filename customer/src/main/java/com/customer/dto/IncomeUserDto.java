package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/16 0016.
 */
@ApiModel(value = "收益角色")
public class IncomeUserDto {

    @ApiModelProperty(value = "用户id")
    private int userId;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "总收益")
    private String totalIncome;

    @ApiModelProperty(value = "商家总数量(levelId:1,高级推广员 2,推广员 可见)")
    private String totalNums;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getTotalNums() {
        return totalNums;
    }

    public void setTotalNums(String totalNums) {
        this.totalNums = totalNums;
    }
}
