package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/17 0017.
 */
@ApiModel(value = "收益走势")
public class IncomeChangeDto {

    @ApiModelProperty(value = "变动时间")
    private Date bdsj;

    @ApiModelProperty(value = "收益")
    private String bdsz;

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }

    public String getBdsz() {
        return bdsz;
    }

    public void setBdsz(String bdsz) {
        this.bdsz = bdsz;
    }
}
