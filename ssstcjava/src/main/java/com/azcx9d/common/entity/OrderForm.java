package com.azcx9d.common.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单
 *
 * @author fby
 */
public class OrderForm {
    private long id;
    private String orderCode;
    private long userId;
    private int storeId;//商家id(businessId)
    private double money;
    private int state;
    private Date createTime;
    private Date caiwu_time;//财务确认时间
    private Date sellerTime;//商家确认时间
    private long goods_id;//商品id
    private float rangli;//商家让利
    private long huikuanId;//回款关联_ID
    private int sellerId;//店主ID_用于查询关联店主所有订单

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
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

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCaiwu_time() {
        return caiwu_time;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setCaiwu_time(Date caiwu_time) {
        this.caiwu_time = caiwu_time;
    }

    public Date getSellerTime() {
        return sellerTime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public void setSellerTime(Date sellerTime) {
        this.sellerTime = sellerTime;
    }

    public long getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(long goods_id) {
        this.goods_id = goods_id;
    }

    public float getRangli() {
        return rangli;
    }

    public void setRangli(float rangli) {
        this.rangli = rangli;
    }

    public long getHuikuanId() {
        return huikuanId;
    }

    public void setHuikuanId(long huikuanId) {
        this.huikuanId = huikuanId;
    }

	public int getSellerId() {
		return sellerId;
	}

	public void setSellerId(int sellerId) {
		this.sellerId = sellerId;
	}

}
