package com.customer.dto.my;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class OpenPushAgentDto {
    @ApiModelProperty(value = "区域id")
    private int areaId;

    @ApiModelProperty(value = "区域名称")
    private String fullName;

    @ApiModelProperty(value = "行业编号")
    private int tradeId;

    @ApiModelProperty(value = "行业名称")
    private String tradeName;

    @ApiModelProperty(value = "已开通个数")
    private int total;

    @ApiModelProperty(value = "可开通个数")
    private int openLimit;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getOpenLimit() {
        return openLimit;
    }

    public void setOpenLimit(int openLimit) {
        this.openLimit = openLimit;
    }

}
