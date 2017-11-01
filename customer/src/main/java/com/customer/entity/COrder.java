package com.customer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/8 0030.
 */
@ApiModel
public class COrder {
    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "自生成编号")
    private String orderId;

    @ApiModelProperty(value = "用户id")
    private int userId;

    @ApiModelProperty(value = "店铺id")
    private int storeId;

    @ApiModelProperty(value = "消费金额")
    private String money;

    @ApiModelProperty(value = "订单状态(0:待商家确认收款,1:商家确认收款,2:财务确认收款)")
    private int state;

    @ApiModelProperty(value = "订单创建时间")
    private Date createTime;

    @ApiModelProperty(value = "财务确认时间")
    private Date caiwuTime;

    @ApiModelProperty(value = "商家确认时间")
    private Date sellerTime;

    @ApiModelProperty(value = "商品id")
    private int goodsId;

    @ApiModelProperty(value = "商家让利比例")
    private String rangli;

    @ApiModelProperty(value = "回款关联_ID")
    private int huikuanId;

    @ApiModelProperty(value = "店主ID_用于查询关联店主所有订单")
    private int sellerId;

    @ApiModelProperty(value = "")
    private String shanxinUser;

    @ApiModelProperty(value = "")
    private String shanxinStore;

    @ApiModelProperty(value = "换算后_消费者剩余积分")
    private String jifenUser;

    @ApiModelProperty(value = "换算后_商户剩余积分")
    private String jifenStore;

    @ApiModelProperty(value = "关闭时间")
    private Date closeTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
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

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public int getHuikuanId() {
        return huikuanId;
    }

    public void setHuikuanId(int huikuanId) {
        this.huikuanId = huikuanId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getShanxinUser() {
        return shanxinUser;
    }

    public void setShanxinUser(String shanxinUser) {
        this.shanxinUser = shanxinUser;
    }

    public String getShanxinStore() {
        return shanxinStore;
    }

    public void setShanxinStore(String shanxinStore) {
        this.shanxinStore = shanxinStore;
    }

    public String getJifenUser() {
        return jifenUser;
    }

    public void setJifenUser(String jifenUser) {
        this.jifenUser = jifenUser;
    }

    public String getJifenStore() {
        return jifenStore;
    }

    public void setJifenStore(String jifenStore) {
        this.jifenStore = jifenStore;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}
