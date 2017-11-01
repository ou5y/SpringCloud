package com.customer.dto.my;

import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/7/20 0020.
 */
public class PushAgentListDto {

    @ApiModelProperty(value = "配送到")
    public String agencyUser;

    @ApiModelProperty(value = "待开通")
    private String phone;

    @ApiModelProperty(value = "行业名称")
    private String tradeName;

    @ApiModelProperty(value = "区域名称")
    private String fullName;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    public String getAgencyUser() {
        return agencyUser;
    }

    public void setAgencyUser(String agencyUser) {
        this.agencyUser = agencyUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
