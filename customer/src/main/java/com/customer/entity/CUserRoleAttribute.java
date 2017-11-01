package com.customer.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class CUserRoleAttribute implements Serializable{
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private BigDecimal shandian;

    private Long integral;

    private Float love;

    private Date createTime;

    private Date lastUpdateTime;

    // 注册时 赠送积分/再消分  动态列名
    private String type;

    // 赠送数量
    private String number;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public BigDecimal getShandian() {
        return shandian;
    }

    public void setShandian(BigDecimal shandian) {
        this.shandian = shandian;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Float getLove() {
        return love;
    }

    public void setLove(Float love) {
        this.love = love;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}