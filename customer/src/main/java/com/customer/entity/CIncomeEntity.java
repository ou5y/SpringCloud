package com.customer.entity;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by fangbaoyan on 2017/5/14.
 */
@ApiModel(value = "个人收益",description = "个人积分,善点")
public class CIncomeEntity {

    @ApiModelProperty("当前")
    private String current;

    @ApiModelProperty(value = "累积")
    private String total;



    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }


}
