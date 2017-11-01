package com.customer.dto.statistic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/7/5 0005.
 */
@ApiModel(value = "收益统计")
public class IncomeStatisticDto {

    @ApiModelProperty(value = "推荐消费奖励")
    private String recommend;

    @ApiModelProperty(value = "业务奖励")
    private String business;

    @ApiModelProperty(value = "区域奖励")
    private String area;

    @ApiModelProperty(value = "行业奖励")
    private String trade;

    @ApiModelProperty(value = "配送")
    private String distribution;

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

}
