package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/16 0016.
 */
@ApiModel(value = "收益详情")
public class IncomeDetailDto {

    @ApiModelProperty(value = "商家ID(店铺ID)")
    private int bId;

    @ApiModelProperty(value = "商家名称(店铺名称)")
    private String bName;

    @ApiModelProperty(value = "变动数值")
    private String bdsz;

    @ApiModelProperty(value = "变动时间")
    private Date bdsj;

    public IncomeDetailDto() {
    }

    public IncomeDetailDto(int bId, String bName, String bdsz, Date bdsj) {
        this.bId = bId;
        this.bName = bName;
        this.bdsz = bdsz;
        this.bdsj = bdsj;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getBdsz() {
        return bdsz;
    }

    public void setBdsz(String bdsz) {
        this.bdsz = bdsz;
    }

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }
}
