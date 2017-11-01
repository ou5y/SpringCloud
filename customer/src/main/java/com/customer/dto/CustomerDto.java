package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by fangbaoyan on 2017/5/8.
 */
@ApiModel(value = "a person",description = "用户注册实体")
public class CustomerDto {
    @ApiModelProperty(value = "phone",notes = "手机号码",required = true)
    private String phone;
    @ApiModelProperty(value = "recommendPhone",notes = "推荐人手机号码")
    private String recommendPhone;
    @ApiModelProperty(value = "password",notes = "登陆密码")
    private String password;
    @ApiModelProperty(value = "checkCode",notes = "验证码")
    private String checkCode;

    public CustomerDto(String phone, String recommendPhone, String password, String checkCode) {
        this.phone = phone;
        this.recommendPhone = recommendPhone;
        this.password = password;
        this.checkCode = checkCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCheckCode() {
        return checkCode;
    }

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }
}
