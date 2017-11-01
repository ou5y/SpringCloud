package com.azcx9d.mybatisgenerator.model;

import java.util.Date;

public class OrderForm extends OrderFormKey {
    private String orderId;

    private Integer userId;

    private Integer storeId;

    private Double money;

    private Integer state;

    private Date createTime;

    private Date caiwuTime;

    private Date sellerTime;

    private Long goodsId;

    private Double rangli;

    private Integer huikuanId;

    private Integer sellerId;

    private Double shanxinUser;

    private Double shanxinStore;

    private Double jifenUser;

    private Double jifenStore;

    private Double ranliMoney;

    private String voucherpic;

    private Integer ordersource;

    private Long caiwuId;

    private String consumptionpic;

    private Date closeTime;

    private Integer quotaType;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCaiwuTime() {
        return caiwuTime;
    }

    public void setCaiwuTime(Date caiwuTime) {
        this.caiwuTime = caiwuTime;
    }

    public Date getSellerTime() {
        return sellerTime;
    }

    public void setSellerTime(Date sellerTime) {
        this.sellerTime = sellerTime;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Double getRangli() {
        return rangli;
    }

    public void setRangli(Double rangli) {
        this.rangli = rangli;
    }

    public Integer getHuikuanId() {
        return huikuanId;
    }

    public void setHuikuanId(Integer huikuanId) {
        this.huikuanId = huikuanId;
    }

    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public Double getShanxinUser() {
        return shanxinUser;
    }

    public void setShanxinUser(Double shanxinUser) {
        this.shanxinUser = shanxinUser;
    }

    public Double getShanxinStore() {
        return shanxinStore;
    }

    public void setShanxinStore(Double shanxinStore) {
        this.shanxinStore = shanxinStore;
    }

    public Double getJifenUser() {
        return jifenUser;
    }

    public void setJifenUser(Double jifenUser) {
        this.jifenUser = jifenUser;
    }

    public Double getJifenStore() {
        return jifenStore;
    }

    public void setJifenStore(Double jifenStore) {
        this.jifenStore = jifenStore;
    }

    public Double getRanliMoney() {
        return ranliMoney;
    }

    public void setRanliMoney(Double ranliMoney) {
        this.ranliMoney = ranliMoney;
    }

    public String getVoucherpic() {
        return voucherpic;
    }

    public void setVoucherpic(String voucherpic) {
        this.voucherpic = voucherpic == null ? null : voucherpic.trim();
    }

    public Integer getOrdersource() {
        return ordersource;
    }

    public void setOrdersource(Integer ordersource) {
        this.ordersource = ordersource;
    }

    public Long getCaiwuId() {
        return caiwuId;
    }

    public void setCaiwuId(Long caiwuId) {
        this.caiwuId = caiwuId;
    }

    public String getConsumptionpic() {
        return consumptionpic;
    }

    public void setConsumptionpic(String consumptionpic) {
        this.consumptionpic = consumptionpic == null ? null : consumptionpic.trim();
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Integer getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(Integer quotaType) {
        this.quotaType = quotaType;
    }
}