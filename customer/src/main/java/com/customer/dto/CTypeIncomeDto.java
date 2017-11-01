package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/7 0007.
 */
@ApiModel(value = "CTypeIncomeDto",description = "不同类型收益")
public class CTypeIncomeDto {

    @ApiModelProperty(value = "收益类型")
    private String grantType;

    @ApiModelProperty(value = "收益总额")
    private String totalIncome;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }
}
