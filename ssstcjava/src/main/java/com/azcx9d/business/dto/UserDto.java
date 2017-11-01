package com.azcx9d.business.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by fangbaoyan on 2017/5/9.
 */

@ApiModel(value = "用户", description = "用户个人信息")

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class UserDto {

    @ApiModelProperty(value = "用户唯一标识", notes = "用户唯一标识")
    private int id;

    @ApiModelProperty(value = "商户名", notes = "商户名")

    private String userName;

    @ApiModelProperty(value = "手机号码", notes = "手机号码")
    private String phone;

    @ApiModelProperty(value = "用户头像", notes = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "我的积分", notes = "积分", dataType = "String")
    private String integral;

    @ApiModelProperty(value = "主动善点", notes = "主动善点", dataType = "String")
    private String shandian;

    @ApiModelProperty(value = "推荐奖励善点", notes = "推荐奖励善点", dataType = "String")
    private String recommendEarnings;

    @ApiModelProperty(value = "token", notes = "token")
    private String token;


//    @ApiModelProperty(value = "今日善心(小额)")
//    private String todayLove = "0.0";
//
//    @ApiModelProperty(value = "今日善心(大额)")
//    private String loveLarge="0.0";
//
//    @ApiModelProperty(value = "今日善心(中额)")
//    private String loveMiddle="0.0";

    @ApiModelProperty(value = "店铺列表", notes = "店铺列表")
    private List<StoreDto> storeDtoList;

    @ApiModelProperty(value = "是否设置交易密码", notes = "(0:未设置,1:已设置)", dataType = "Boolean")
    private Boolean hasTransPwd;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getShandian() {
        return shandian;
    }

    public void setShandian(String shandian) {
        this.shandian = shandian;
    }

    public String getRecommendEarnings() {
        return recommendEarnings;
    }

    public void setRecommendEarnings(String recommendEarnings) {
        this.recommendEarnings = recommendEarnings;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }




    public Boolean getHasTransPwd() {
        return hasTransPwd;
    }

    public void setHasTransPwd(Boolean hasTransPwd) {
        this.hasTransPwd = hasTransPwd;
    }



    public List<StoreDto> getStoreDtoList() {
        return storeDtoList;
    }

    public void setStoreDtoList(List<StoreDto> storeDtoList) {
        this.storeDtoList = storeDtoList;
    }

//    public String getLoveLarge() {
//        return loveLarge;
//    }
//
//    public void setLoveLarge(String loveLarge) {
//        this.loveLarge = loveLarge;
//    }
//
//    public String getLoveMiddle() {
//        return loveMiddle;
//    }
//
//    public void setLoveMiddle(String loveMiddle) {
//        this.loveMiddle = loveMiddle;
//    }
//    public String getTodayLove() {
//        return todayLove;
//    }
//
//    public void setTodayLove(String todayLove) {
//        if (todayLove != null) {
//            this.todayLove = todayLove;
//        }
//
//    }
}
