package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/28 0028.
 */
@ApiModel("自由支付订单")
public class FreeOrderDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "订单编号")
    private String orderId;

    @ApiModelProperty(value = "金额")
    private String money;

    @ApiModelProperty(value = "让利")
    private String rangli;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "消费凭证")
    private String consumptionPic;

    @ApiModelProperty(value = "店铺id")
    private String storeId;

    @ApiModelProperty(value = "订单状态(3:已完结, -1:商家关闭, -2:审核失败)")
    private int state;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "完结时间")
    private Date caiwuTime;

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

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsumptionPic() {
        return consumptionPic;
    }

    public void setConsumptionPic(String consumptionPic) {
        this.consumptionPic = consumptionPic;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
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

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }
}
