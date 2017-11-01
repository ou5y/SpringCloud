package com.azcx9d.scheduled.dto;

/**
 * Created by fangbaoyan on 2017/4/11.
 */
public class OderFormDto {

    private int businessId;
    private double money;
    private int maxAmount;

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}
