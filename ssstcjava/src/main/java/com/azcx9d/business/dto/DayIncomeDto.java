package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/30 0030.
 */
@ApiModel(value = "天收益")
public class DayIncomeDto {

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "让利(上传比例)")
    private String rangli;

    @ApiModelProperty(value = "订单金额")
    private String money;

    @ApiModelProperty(value = "订单来源：1：扫码(网络支付) 2：录单(自由支付)")
    private int orderSource;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getOrderSource() {
        return orderSource;
    }

    public void setOrderSource(int orderSource) {
        this.orderSource = orderSource;
    }

}
