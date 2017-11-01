package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel(value = "兑换金额手续费")
public class CExchangeMoneyDto {

    @ApiModelProperty(value = "手续费")
    private String poundage;

    @ApiModelProperty(value = "实际到账")
    private String sjdk;

    public CExchangeMoneyDto() {
    }

    public CExchangeMoneyDto(String poundage, String sjdk) {
        this.poundage = poundage;
        this.sjdk = sjdk;
    }

    public String getPoundage() {
        return poundage;
    }

    public void setPoundage(String poundage) {
        this.poundage = poundage;
    }

    public String getSjdk() {
        return sjdk;
    }

    public void setSjdk(String sjdk) {
        this.sjdk = sjdk;
    }
}
