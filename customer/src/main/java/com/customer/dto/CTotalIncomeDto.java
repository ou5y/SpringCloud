package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/7 0007.
 */
@ApiModel(value = "CTotalIncomeDto",description = "收益管理总额")
public class CTotalIncomeDto {

    @ApiModelProperty(value = "总收益额")
    private String totalIncome;

    @ApiModelProperty(value = "区域收益总额")
    private String areaIncome;

    @ApiModelProperty(value = "推荐消费奖励")
    private String recommendIncome;

    @ApiModelProperty(value = "业务奖励收益总额")
    private String businessIncome;

    @ApiModelProperty(value = "行业收益总额")
    private String tradeIncome;

    @ApiModelProperty(value = "配送收益总额")
    private String distributionIncome;

    public String getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getAreaIncome() {
        return areaIncome;
    }

    public void setAreaIncome(String areaIncome) {
        this.areaIncome = areaIncome;
    }

    public String getRecommendIncome() {
        return recommendIncome;
    }

    public void setRecommendIncome(String recommendIncome) {
        this.recommendIncome = recommendIncome;
    }

    public String getBusinessIncome() {
        return businessIncome;
    }

    public void setBusinessIncome(String businessIncome) {
        this.businessIncome = businessIncome;
    }

    public String getTradeIncome() {
        return tradeIncome;
    }

    public void setTradeIncome(String tradeIncome) {
        this.tradeIncome = tradeIncome;
    }

    public String getDistributionIncome() {
        return distributionIncome;
    }

    public void setDistributionIncome(String distributionIncome) {
        this.distributionIncome = distributionIncome;
    }

}
