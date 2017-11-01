package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel(value = "TotalExchangeDto",description = "累计兑换总额")
public class TotalExchangeDto {

    @ApiModelProperty(value = "累计兑换总额")
    private String totalExchange;

    public String getTotalExchange() {
        return totalExchange;
    }

    public void setTotalExchange(String totalExchange) {
        this.totalExchange = totalExchange;
    }
}
