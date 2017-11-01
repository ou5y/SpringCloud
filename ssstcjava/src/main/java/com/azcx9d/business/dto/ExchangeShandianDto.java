package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by chenxl on 2017/5/11 0011.
 */
@ApiModel(value = "兑换善点和被动善点")
public class ExchangeShandianDto {

    @ApiModelProperty(value = "主动善点")
    private String shandian;

    @ApiModelProperty(value = "推荐奖励善点")
    private String recommendShandian;

    @ApiModelProperty(value = "银行卡列表")
    private List<ExchangeBankcardDto> cExchangeBankcardDto;

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

    public List<ExchangeBankcardDto> getcExchangeBankcardDto() {
        return cExchangeBankcardDto;
    }

    public void setcExchangeBankcardDto(List<ExchangeBankcardDto> cExchangeBankcardDto) {
        this.cExchangeBankcardDto = cExchangeBankcardDto;
    }
}
