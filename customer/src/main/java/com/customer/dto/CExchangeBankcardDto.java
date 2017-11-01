package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/11 0011.
 */
@ApiModel(value = "银行卡信息")
public class CExchangeBankcardDto {

    @ApiModelProperty(value = "主键id")
    private int id;

    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;

    @ApiModelProperty(value = "开户行名称")
    private String bankName;

    @ApiModelProperty(value="持卡人名称")
    private String cardHolder;

    public CExchangeBankcardDto() {
    }

    public CExchangeBankcardDto(int id, String bankCardNo, String bankName, String cardHolder) {
        this.id = id;
        this.bankCardNo = bankCardNo;
        this.bankName = bankName;
        this.cardHolder = cardHolder;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
}
