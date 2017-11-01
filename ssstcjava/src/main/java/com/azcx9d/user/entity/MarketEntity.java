package com.azcx9d.user.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * Created by fangbaoyan on 2017/4/1.
 */
public class MarketEntity {
    private int id;
    private double expenditure;//单日消费金额
    private float oldLove;//沉淀善心
    private float newLove;//当天善心
    private float lovePercentage;//善心(比例）
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private double lovePoint;//当天善点
    private double surplusIntegal;//剩余积分

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(double expenditure) {
        this.expenditure = expenditure;
    }

    public float getOldLove() {
        return oldLove;
    }

    public void setOldLove(float oldLove) {
        this.oldLove = oldLove;
    }

    public float getNewLove() {
        return newLove;
    }

    public void setNewLove(float newLove) {
        this.newLove = newLove;
    }

    public float getLovePercentage() {
        return lovePercentage;
    }

    public void setLovePercentage(float lovePercentage) {
        this.lovePercentage = lovePercentage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public double getLovePoint() {
        return lovePoint;
    }

    public void setLovePoint(double lovePoint) {
        this.lovePoint = lovePoint;
    }

    public double getSurplusIntegal() {
        return surplusIntegal;
    }

    public void setSurplusIntegal(double surplusIntegal) {
        this.surplusIntegal = surplusIntegal;
    }
}
