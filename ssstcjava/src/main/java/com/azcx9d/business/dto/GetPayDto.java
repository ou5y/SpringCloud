package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/8/22 0011.
 */
@ApiModel(value = "去收款")
public class GetPayDto {

    @ApiModelProperty(value = "商户订单号")
    private String orderId;

    @ApiModelProperty(value = "订单金额")
    private String money;

    @ApiModelProperty(value = "商户名称")
    private String bName;

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

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }
}
