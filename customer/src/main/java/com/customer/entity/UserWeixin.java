package com.customer.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by fangbaoyan on 2017/4/24.
 */
@Data
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
@Entity
@Table(name = "userWeixin")
public class UserWeixin {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "pass")
    private String password;

    @Column(name = "phone")
    private String phone;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "integral")
    private BigInteger integral;

    @Column(name = "shandian")
    private BigDecimal shandian;

    @Column(name = "love")
    private String love;

    @Column(name = "levelName")
    private String levelName;

    @Column(name = "openid")
    private String openid;

    private int parentId;

    private int directlyId;

    private String recommendPhone;

    private String userName;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public BigInteger getIntegral() {
        return integral;
    }

    public void setIntegral(BigInteger integral) {
        this.integral = integral;
    }

    public BigDecimal getShandian() {
        return shandian;
    }

    public void setShandian(BigDecimal shandian) {
        this.shandian = shandian;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecommendPhone() {
        return recommendPhone;
    }

    public void setRecommendPhone(String recommendPhone) {
        this.recommendPhone = recommendPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getDirectlyId() {
        return directlyId;
    }

    public void setDirectlyId(int directlyId) {
        this.directlyId = directlyId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
