package com.azcx9d.business.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by HuangQing on 2017/3/30 0030.
 */
//@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BOrderForm {

    @ApiModelProperty(value = "主键id")
    private int id;            //id

    @ApiModelProperty(value = "订单编号")
    private String orderId;     //订单id

    @ApiModelProperty(value = "录入用户id")
    private int userId;        //录入用户id

    @ApiModelProperty(value = "店铺id")
    private int storeId;       //店铺id

    @ApiModelProperty(value = "消费金额")
    private double money;   //消费金额

    @ApiModelProperty(value = "订单状态(0:待商家确认收款,1:商家确认收款,2:财务确认收款)")
    private int state;      //订单状态(0:待商家确认收款,1:商家确认收款,2:财务确认收款)

    @ApiModelProperty(value = "创建时间")
    private Date createTime;    //创建时间

    @ApiModelProperty(value = "财务确认时间")
    private Date caiwuTime;     //财务确认时间

    @ApiModelProperty(value = "商家确认时间")
    private Date sellerTime;    //商家确认时间

    @ApiModelProperty(value = "商品id")
    private int goodsId;       //商品id

    @ApiModelProperty(value = "商家让利比例 默认0.2")
    private double rangli;  //商家让利比例 默认0.2

    @ApiModelProperty(value = "回款关联_ID")
    private int huikuanId;  //回款关联_ID

    @ApiModelProperty(value = "店主ID_用于查询关联店主所有订单")
    private int sellerId;   //店主ID_用于查询关联店主所有订单

    @ApiModelProperty(value = "善心用户")
    private double shanxinUser;    //

    @ApiModelProperty(value = "善心店铺")
    private double shanxinStore;   //

    @ApiModelProperty(value = "换算后_消费者剩余积分")
    private double jifenUser;  //换算后_消费者剩余积分

    @ApiModelProperty(value = "换算后_商户剩余积分")
    private double jifenStore; //换算后_商户剩余积分

    @ApiModelProperty(value = "线下打款凭证")
    private String voucherPic;

    @ApiModelProperty(value = "订单来源：1：扫码(网络支付) 2：录单(自由支付)")
    private int orderSource;

    @ApiModelProperty(value = "消费凭证")
    private String consumptionPic; //消费凭证

    @ApiModelProperty(value = "下单用户手机号码")
    private String userPhone;  //下单用户手机号码

    @ApiModelProperty(value = "关闭时间")
    private Date closeTime;  //关闭时间

    @ApiModelProperty(value = "支付方式")
    private int payType;  //支付方式

    public int getPayType() {
        return this.payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

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

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
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

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
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

    public String getVoucherPic() {
        return voucherPic;
    }

    public void setVoucherPic(String voucherPic) {
        this.voucherPic = voucherPic;
    }

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

    public String getConsumptionPic() {
        return consumptionPic;
    }

    public void setConsumptionPic(String consumptionPic) {
        this.consumptionPic = consumptionPic;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}
