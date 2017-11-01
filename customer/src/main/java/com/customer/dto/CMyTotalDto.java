package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/11 0011.
 */
@ApiModel(value = "我的累计")
public class CMyTotalDto {

    @ApiModelProperty(value = "累计")
    private String total;

    public CMyTotalDto() {
    }

    public CMyTotalDto(String total) {
        this.total = total;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
