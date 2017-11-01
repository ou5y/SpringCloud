package com.customer.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/5/10.
 */
@ApiModel(value = "银行卡",description = "个人银行卡信息")
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class CBankCardDto {
    @ApiModelProperty(value = "主键")
    private int id;
    @ApiModelProperty(value = "银行卡号")
    private String number;
    @ApiModelProperty(value = "银行名称")
    private String bankNmae;
    @ApiModelProperty(value = "是否默认",notes = "0:否,1:是")
    private int isDefault;

    public CBankCardDto(){

    }

    public CBankCardDto(int id, String number, String bankNmae, int isDefault) {
        this.id = id;
        this.number = number;
        this.bankNmae = bankNmae;
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBankNmae() {
        return bankNmae;
    }

    public void setBankNmae(String bankNmae) {
        this.bankNmae = bankNmae;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
