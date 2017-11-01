package com.customer.dto.statistic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/5/17.
 */
@ApiModel(value = "代理业务",description = "代理区域,收益")
public class AgencyAreaDto {

    @ApiModelProperty(value = "区域id")
    private int areaId;

    @ApiModelProperty(value = "区域名称")
    private String fullName;

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

}
