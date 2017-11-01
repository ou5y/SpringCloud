package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@ApiModel(value = "今日统计")
public class TodayStatisticsDto {

    @ApiModelProperty(value = "消费总额")
    private String totalAmount;

    @ApiModelProperty(value = "商家数")
    private String totalBusiness;

    @ApiModelProperty(value = "消费者数")
    private String totalCustomer;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getTotalBusiness() {
        return totalBusiness;
    }

    public void setTotalBusiness(String totalBusiness) {
        this.totalBusiness = totalBusiness;
    }

    public String getTotalCustomer() {
        return totalCustomer;
    }

    public void setTotalCustomer(String totalCustomer) {
        this.totalCustomer = totalCustomer;
    }

}
