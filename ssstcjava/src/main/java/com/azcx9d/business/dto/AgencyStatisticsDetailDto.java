package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ApiModel(value = "积分明细")
public class AgencyStatisticsDetailDto {

    @ApiModelProperty(value = "积分来源,-1 赠送  0=消费者0.6   1=大小区总监副总  2=区域代理  3=行业代理")
    private String grantType;

    @ApiModelProperty(value = "创建时间")
    private Date bdsj;

    @ApiModelProperty(value = "类型 0=主动善点  1=积分  2=善心  3=被动善点")
    private String leixing;

    @ApiModelProperty(value = "所得积分")
    private int bdsz;

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }

    public String getLeixing() {
        return leixing;
    }

    public void setLeixing(String leixing) {
        this.leixing = leixing;
    }

    public int getBdsz() {
        return bdsz;
    }

    public void setBdsz(int bdsz) {
        this.bdsz = bdsz;
    }

}
