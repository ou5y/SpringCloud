package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/5/28.
 */
@ApiModel(value = "商家店铺",description = "商家店铺模型")
public class StoreDto {
    @ApiModelProperty(value = "店铺id",notes = "店铺id")
    private int id;
    @ApiModelProperty(value = "店铺名",notes = "店铺名")
    private String name;
    @ApiModelProperty("善心线-值")
    private String LoveQuotaValue;
    @ApiModelProperty("善心线-类型名称")
    private String LoveQuotaName;
    @ApiModelProperty(value = "店铺额类型",notes = "店铺额类型")
    private int quotaType;

    @ApiModelProperty("封顶金额")
    private String maxAmount;

    @ApiModelProperty(value = "是否接入网络支付",notes = "0:未接入, 1:已接入")
    private int hasNbpay;

    @ApiModelProperty("让利")
    private String rangli;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public String getLoveQuotaValue() {
        return LoveQuotaValue;
    }

    public void setLoveQuotaValue(String loveQuotaValue) {
        LoveQuotaValue = loveQuotaValue;
    }

    public String getLoveQuotaName() {
        return LoveQuotaName;
    }

    public void setLoveQuotaName(String loveQuotaName) {
        LoveQuotaName = loveQuotaName;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public int getHasNbpay() {
        return hasNbpay;
    }

    public void setHasNbpay(int hasNbpay) {
        this.hasNbpay = hasNbpay;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }
}
