package com.customer.dto.statistic;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/5 0005.
 */
@ApiModel(value = "今日收益")
public class IncomeDetailDto {

    @ApiModelProperty(value = "变动数值")
    private String bdsz;

    @ApiModelProperty(value = "发放类型,  0=推荐消费奖励   1=业务奖励  2=区域奖励  3=行业奖励   4=配送")
    private int grantType;

    @ApiModelProperty(value = "额线,  0=低额 1=小额 2=中额 3=大额 ")
    private int quotaType;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getBdsz() {
        return bdsz;
    }

    public void setBdsz(String bdsz) {
        this.bdsz = bdsz;
    }

    public int getGrantType() {
        return grantType;
    }

    public void setGrantType(int grantType) {
        this.grantType = grantType;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
