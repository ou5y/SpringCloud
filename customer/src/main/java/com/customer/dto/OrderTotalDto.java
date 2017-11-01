package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/17 0017.
 */
@ApiModel("订单总额")
public class OrderTotalDto {

    @ApiModelProperty(value = "订单总额")
    private String totalMoney;

    public String getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(String totalMoney) {
        this.totalMoney = totalMoney;
    }
}
