package com.azcx9d.consumer.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by chenxl on 2017/3/30 0030.
 */
@ApiModel
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class OrderEntity {
    @ApiModelProperty(value = "id")
    private long id;

    @ApiModelProperty(value = "自生成编号")
    private String orderId;

    @ApiModelProperty(value = "用户id")
    private long userId;

    @ApiModelProperty(value = "店铺id")
    private long storeId;

    @ApiModelProperty(value = "消费金额")
    private double money;

    @ApiModelProperty(value = "订单状态(0:待商家确认收款,1:商家确认收款,2:财务确认收款)")
    private int state;

    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "财务确认时间")
    private Date caiwuTime;

    @ApiModelProperty(value = "商家确认时间")
    private Date sellerTime;

    @ApiModelProperty(value = "商品id")
    private long goodsId;

    @ApiModelProperty(value = "商家让利比例")
    private double rangli;

    @ApiModelProperty(value = "回款关联_ID")
    private int huikuanId;

    @ApiModelProperty(value = "店主ID_用于查询关联店主所有订单")
    private long sellerId;

    @ApiModelProperty(value = "")
    private double shanxinUser;

    @ApiModelProperty(value = "")
    private double shanxinStore;

    @ApiModelProperty(value = "换算后_消费者剩余积分")
    private double jifenUser;

    @ApiModelProperty(value = "换算后_商户剩余积分")
    private double jifenStore;

    @ApiModelProperty(value = "关闭时间")
    private Date closeTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
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

    public long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(long goodsId) {
        this.goodsId = goodsId;
    }

    public double getRangli() {
        return rangli;
    }

    public void setRangli(double rangli) {
        this.rangli = rangli;
    }

    public int getHuikuanId() {
        return huikuanId;
    }

    public void setHuikuanId(int huikuanId) {
        this.huikuanId = huikuanId;
    }

    public long getSellerId() {
        return sellerId;
    }

    public void setSellerId(long sellerId) {
        this.sellerId = sellerId;
    }

    public double getShanxinUser() {
        return shanxinUser;
    }

    public void setShanxinUser(double shanxinUser) {
        this.shanxinUser = shanxinUser;
    }

    public double getShanxinStore() {
        return shanxinStore;
    }

    public void setShanxinStore(double shanxinStore) {
        this.shanxinStore = shanxinStore;
    }

    public double getJifenUser() {
        return jifenUser;
    }

    public void setJifenUser(double jifenUser) {
        this.jifenUser = jifenUser;
    }

    public double getJifenStore() {
        return jifenStore;
    }

    public void setJifenStore(double jifenStore) {
        this.jifenStore = jifenStore;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}
