package com.azcx9d.business.entity;

import com.azcx9d.user.entity.User;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import springfox.documentation.annotations.ApiIgnore;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
public class BUser extends User {

    @ApiModelProperty(value = "再销分")
    private double reusePoint;



    private String todayLove;

    @ApiModelProperty(value = "直属id")
    private int directlyId;

    @ApiModelProperty(value = "是否设置交易密码", notes = "(0:未设置,1:已设置)", dataType = "Boolean")
    private Boolean hasTransPwd;

    private String loveLarge;

    private String loveMiddle;

    private String loveSmall;

    public BUser(){
        super();
    }

    public BUser(String phone,String password){
        super();
        super.setPhone(phone);
        super.setPassword(password);
    }

    @ApiModelProperty(hidden = true)
    public void setPass(String password){
        super.setPassword(password);
    }

    @ApiModelProperty(hidden = true)
    public String getPass() {
        return super.getPassword();
    }

    public double getReusePoint() {
        return reusePoint;
    }

    public void setReusePoint(double reusePoint) {
        this.reusePoint = reusePoint;
    }


    public String getTodayLove() {
        return todayLove;
    }

    public void setTodayLove(String todayLove) {
        this.todayLove = todayLove;
    }

    public int getDirectlyId() {
        return directlyId;
    }

    public void setDirectlyId(int directlyId) {
        this.directlyId = directlyId;
    }

    public Boolean getHasTransPwd() {
        return hasTransPwd;
    }

    public void setHasTransPwd(Boolean hasTransPwd) {
        this.hasTransPwd = hasTransPwd;
    }

    public String getLoveLarge() {
        return loveLarge;
    }

    public void setLoveLarge(String loveLarge) {
        this.loveLarge = loveLarge;
    }

    public String getLoveMiddle() {
        return loveMiddle;
    }

    public void setLoveMiddle(String loveMiddle) {
        this.loveMiddle = loveMiddle;
    }

    public String getLoveSmall() {
        return loveSmall;
    }

    public void setLoveSmall(String loveSmall) {
        this.loveSmall = loveSmall;
    }
}
