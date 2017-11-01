package com.customer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel("秒杀")
public class CSecondKill {

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "商品图片")
    private String goodsImg;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "原价")
    private String originalPrice;

    @ApiModelProperty(value = "剩余数量")
    private Integer quantity;

    @ApiModelProperty(value = "售出数量")
    private Integer saleOut;

    @ApiModelProperty(value = "开始时间")
    private Date startTime;

    @ApiModelProperty(value = "结束时间")
    private Date endTime;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "秒杀活动描述")
    private String killDesc;

    @ApiModelProperty(value = "关注数")
    private Integer concernNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg == null ? null : goodsImg.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getSaleOut() {
        return saleOut;
    }

    public void setSaleOut(Integer saleOut) {
        this.saleOut = saleOut;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getKillDesc() {
        return killDesc;
    }

    public void setKillDesc(String killDesc) {
        this.killDesc = killDesc;
    }

    public Integer getConcernNumber() {
        return concernNumber;
    }

    public void setConcernNumber(Integer concernNumber) {
        this.concernNumber = concernNumber;
    }

}