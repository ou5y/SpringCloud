package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/6/2 0002.
 */
@ApiModel(value = "核对商家信息")
public class CheckBusinessDto {

    @ApiModelProperty(value = "商家id")
    private Integer id;

    @ApiModelProperty(value = "商家名")
    private String bName;

    @ApiModelProperty(value = "店主")
    private Integer userId;

    @ApiModelProperty(value = "封顶限额")
    private String maxAmount;

    @ApiModelProperty(value = "1 小额线,2 中额线 3,大额线")
    private Integer quotaType;

    @ApiModelProperty(value = "是否是会员(0:不是 大于0:是)")
    private Integer isMember;

    @ApiModelProperty(value = "消费者和商家是否同一人")
    private Integer isSelf;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Integer getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(Integer quotaType) {
        this.quotaType = quotaType;
    }

    public Integer getIsMember() {
        return isMember;
    }

    public void setIsMember(Integer isMember) {
        this.isMember = isMember;
    }

    public Integer getIsSelf() {
        return isSelf;
    }

    public void setIsSelf(Integer isSelf) {
        this.isSelf = isSelf;
    }
}
