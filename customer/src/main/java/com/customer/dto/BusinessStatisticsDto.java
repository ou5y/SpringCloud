package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/6/3 0003.
 */
@ApiModel(value = "商家管理")
public class BusinessStatisticsDto {

    @ApiModelProperty(value = "总收益")
    private String total;

    @ApiModelProperty(value = "店铺个数")
    private int count;

    @ApiModelProperty(value = "商户id")
    private int userId;

    @ApiModelProperty(value = "商户名称")
    private String name;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
