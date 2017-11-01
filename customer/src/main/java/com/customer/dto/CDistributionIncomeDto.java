package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/7/8 0008.
 */
@ApiModel(value = "CDistributionIncomeDto",description = "配送收益详情")
public class CDistributionIncomeDto {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "姓名")
    private String xingming;

    @ApiModelProperty(value = "商家名称")
    private String bName;

    @ApiModelProperty(value = "变动收益")
    private String bdsz;

    @ApiModelProperty(value = "变动时间")
    private Date bdsj;

    @ApiModelProperty(value = "行业名称")
    private String tradeName;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
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

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
