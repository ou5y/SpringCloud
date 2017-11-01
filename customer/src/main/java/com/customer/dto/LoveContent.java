package com.customer.dto;

import com.google.common.base.Objects;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/6/3.
 */
@ApiModel("善心线")
public class LoveContent implements Comparable<LoveContent>{
    @ApiModelProperty("善心线-值")
    private String LoveQuotaValue;
    @ApiModelProperty("善心线-类型名称")
    private String LoveQuotaName;
    @ApiModelProperty("善心线-类型")
    private int LoveQuotaType;

    public LoveContent(String loveQuotaValue, String loveQuotaName, int loveQuotaType) {
        LoveQuotaValue = loveQuotaValue;
        LoveQuotaName = loveQuotaName;
        LoveQuotaType = loveQuotaType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoveContent{");
        sb.append("LoveQuotaValue=").append(LoveQuotaValue).append('\'');
        sb.append("LoveQuotaName=").append(LoveQuotaName).append('\'');
        sb.append("LoveQuotaType=").append(LoveQuotaType).append('\'');
        sb.append('}');
        return sb.toString();
    }
    public LoveContent setLoveQuotaValue(String loveQuotaValue){
        LoveQuotaValue=loveQuotaValue;
        return this;
    }

    public LoveContent setLoveQuotaName(String loveQuotaName)
    {
        LoveQuotaName = loveQuotaName;
        return this;
    }

    public LoveContent setLoveQuotaType(int quotaType) {
       LoveQuotaType = quotaType;
        return this;
    }

    public String getLoveQuotaValue() {
        return LoveQuotaValue;
    }
    public String getLoveQuotaName() {
        return LoveQuotaName;
    }

    public int getLoveQuotaType() {
        return LoveQuotaType;
    }

    @Override
    public int compareTo(LoveContent loveContent) {
        return this.getLoveQuotaValue().compareTo(loveContent.getLoveQuotaValue());
    }
}
