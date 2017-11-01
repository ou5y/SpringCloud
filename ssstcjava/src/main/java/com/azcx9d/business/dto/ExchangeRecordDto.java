package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@ApiModel(value = "兑换记录")
public class ExchangeRecordDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "兑换善点")
    private String cShandian;

    @ApiModelProperty(value = "付款状态0：待付款，1：已付款)")
    private int state;

    @ApiModelProperty(value = "到账方式")
    private int arrivalMode;

    @ApiModelProperty(value = "创建时间")
    private Date createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getcShandian() {
        return cShandian;
    }

    public void setcShandian(String cShandian) {
        this.cShandian = cShandian;
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

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
