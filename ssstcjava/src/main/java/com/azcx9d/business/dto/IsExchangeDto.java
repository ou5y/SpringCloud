package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/15 0015.
 */
@ApiModel(value = "是否可兑换")
public class IsExchangeDto {

    @ApiModelProperty(value = "今日善心")
    private String todayLove;

    @ApiModelProperty(value = "善点")
    private String shandian;

    @ApiModelProperty(value = "推荐奖励善点")
    private String recommendShandian;

    @ApiModelProperty(value = "再消分")
    private String reusePoint;

    @ApiModelProperty(value = "是否可兑换(0:不能兑换, 1:可兑换)")
    private int isExchange;

    @ApiModelProperty(value = "兑换说明")
    private String exchangeDesc;

    public String getTodayLove() {
        return todayLove;
    }

    public void setTodayLove(String todayLove) {
        this.todayLove = todayLove;
    }

    public String getShandian() {
        return shandian;
    }

    public void setShandian(String shandian) {
        this.shandian = shandian;
    }

    public String getRecommendShandian() {
        return recommendShandian;
    }

    public void setRecommendShandian(String recommendShandian) {
        this.recommendShandian = recommendShandian;
    }

    public String getReusePoint() {
        return reusePoint;
    }

    public void setReusePoint(String reusePoint) {
        this.reusePoint = reusePoint;
    }

    public int getIsExchange() {
        return isExchange;
    }

    public void setIsExchange(int isExchange) {
        this.isExchange = isExchange;
    }

    public String getExchangeDesc() {
        return exchangeDesc;
    }

    public void setExchangeDesc(String exchangeDesc) {
        this.exchangeDesc = exchangeDesc;
    }
}
