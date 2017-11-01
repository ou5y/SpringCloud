package com.customer.dto;

import com.customer.enums.QuataEnum;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javafx.collections.transformation.SortedList;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fangbaoyan on 2017/5/9.
 */
@Data
@ApiModel(value = "用户",description = "用户个人信息")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class UserDto {

    @ApiModelProperty(value = "善心",notes = "善心")
    private List<LoveContent> loveContents = new ArrayList<>();

    @ApiModelProperty(value = "用户唯一标识",notes = "用户唯一标识")
    private int id;

    private int userId;

    @ApiModelProperty(value = "用户名",notes = "用户名")
    private String userName;

    @ApiModelProperty(value = "手机号码",notes = "手机号码")
    private String phone;

    @ApiModelProperty(value = "用户头像",notes = "用户头像")
    private String avatar;

    @ApiModelProperty(value = "我的积分",notes = "积分",dataType ="String")
    private String integral;

    @ApiModelProperty(value = "主动善点",notes = "主动善点",dataType ="String")
    private String shandian;

    @ApiModelProperty(value = "推荐奖励善点",notes = "推荐奖励善点",dataType ="String")
    private String recommendEarnings;

    @ApiModelProperty(value = "身份",notes = "身份")
    private String levelName;

    @ApiModelProperty(value = "身份id",notes = "身份id")
    private int levelId;

    @ApiModelProperty(value = "token",notes = "token")
    private String token;

    @ApiModelProperty(value = "是否认证",notes = "(0:未认证,1:已认证)",dataType = "Boolean")
    private Boolean hasBankCard;

    @ApiModelProperty(value = "今日善心(小额)")
    private String loveSmall="0.0";
    @ApiModelProperty(value = "今日善心(中额)")
    private String loveMiddle="0.0";
    @ApiModelProperty(value = "今日善心(大额)")
    private String loveLarge="0.0";
    @ApiModelProperty(value = "今日善心(综合)")
    private String todayLove;



    @ApiModelProperty(value = "角色",notes = "角色")
    private List<RoleListDto> roleListDto;

    @ApiModelProperty(value = "是否设置交易密码",notes = "(0:未设置,1:已设置)",dataType = "Boolean")
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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boolean getHasBankCard() {
        return hasBankCard;
    }

    public void setHasBankCard(Boolean hasBankCard) {
        this.hasBankCard = hasBankCard;
    }



    public List<RoleListDto> getRoleListDto() {
        return roleListDto;
    }

    public void setRoleListDto(List<RoleListDto> roleListDto) {
        this.roleListDto = roleListDto;
    }

    public int getLevelId() {
        return levelId;
    }

    public void setLevelId(int levelId) {
        this.levelId = levelId;
    }

    public Boolean getHasTransPwd() {
        return hasTransPwd;
    }

    public void setHasTransPwd(Boolean hasTransPwd) {
        this.hasTransPwd = hasTransPwd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public List<LoveContent> getLoveContents() {
        return loveContents;
    }

    public void setLoveContents(List<LoveContent> loveContents) {
        this.loveContents = loveContents;
    }

    public String getLoveSmall() {
        return loveSmall;
    }

    public void setLoveSmall(String loveSmall) {
        this.loveSmall = loveSmall;
    }

    public String getLoveMiddle() {
        return loveMiddle;
    }

    public void setLoveMiddle(String loveMiddle) {
        this.loveMiddle = loveMiddle;
    }

    public String getLoveLarge() {
        return loveLarge;
    }

    public void setLoveLarge(String loveLarge) {
        this.loveLarge = loveLarge;
    }

    public String getTodayLove() {
        return todayLove;
    }

    public void setTodayLove(String todayLove) {
        this.todayLove = todayLove;
    }

}
