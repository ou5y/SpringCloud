package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * Created by fangbaoyan on 2017/5/17.
 */
@ApiModel(value = "代理业务",description = "代理区域,收益")
public class AgencyBenefitsDto {

    @ApiModelProperty(value = "区域id")
    private int areaId;

    @ApiModelProperty(value = "区域名称")
    private String areaName;

    @ApiModelProperty(value = "总收益")
    private String total;

    @ApiModelProperty(value = "下级代理数")
    private int count;

    @ApiModelProperty(value = "行业编号")
    private int tradeId;

    @ApiModelProperty(value = "行业名称")
    private String tradeName;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getTradeId() {
        return tradeId;
    }

    public void setTradeId(int tradeId) {
        this.tradeId = tradeId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

}
