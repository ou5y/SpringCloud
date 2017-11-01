package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/11 0011.
 */
@ApiModel(value = "兑换后剩余善点,被动善点,积分")
public class CExchangedShandianDto {

    @ApiModelProperty(value = "主动善点")
    private String shandian;

    @ApiModelProperty(value = "推荐奖励善点")
    private String recommendShandian;

    @ApiModelProperty(value = "积分")
    private String integral;

    public CExchangedShandianDto() {
    }

    public CExchangedShandianDto(String shandian, String recommendShandian, String integral) {
        this.shandian = shandian;
        this.recommendShandian = recommendShandian;
        this.integral = integral;
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

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }
}
