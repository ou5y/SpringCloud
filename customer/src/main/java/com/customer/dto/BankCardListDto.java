package com.customer.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public class BankCardListDto {

    @ApiModelProperty(value = "主键，自增")
    private long id;

    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;

    @ApiModelProperty(value = "开户行名称")
    private String bankName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
