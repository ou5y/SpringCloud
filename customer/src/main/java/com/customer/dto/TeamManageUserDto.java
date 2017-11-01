package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/17 0016.
 */
@ApiModel(value = "用户列表")
public class TeamManageUserDto {

    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "名字")
    private String name;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "总收益")
    private String totalIncome;

    @ApiModelProperty(value = "直接子级")
    private String isChild;

    @ApiModelProperty(value = "推荐人手机号")
    private String recmmendPhone;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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

    public String getIsChild() {
        return isChild;
    }

    public void setIsChild(String isChild) {
        this.isChild = isChild;
    }

    public String getRecmmendPhone() {
        return recmmendPhone;
    }

    public void setRecmmendPhone(String recmmendPhone) {
        this.recmmendPhone = recmmendPhone;
    }
}
