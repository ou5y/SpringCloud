package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel(value = "兑换详情")
public class ExchangeDetailDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    @ApiModelProperty(value = "姓名")
    private String cardHolder;

    @ApiModelProperty(value = "银行")
    private String bankName;

    @ApiModelProperty(value = "银行账号")
    private String bankCardNo;

    @ApiModelProperty(value = "兑换善点")
    private String cShandian;

    @ApiModelProperty(value = "手续费")
    private String poundage;

    @ApiModelProperty(value = "实际到账")
    private String sjdk;

    @ApiModelProperty(value = "付款状态0：待付款，1：已付款)")
    private int state;

    @ApiModelProperty(value = "到账方式")
    private int arrivalMode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getcShandian() {
        return cShandian;
    }

    public void setcShandian(String cShandian) {
        this.cShandian = cShandian;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getArrivalMode() {
        return arrivalMode;
    }

    public void setArrivalMode(int arrivalMode) {
        this.arrivalMode = arrivalMode;
    }
}
