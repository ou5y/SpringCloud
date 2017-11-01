package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/6/10 0010.
 */
@ApiModel(value = "区域详情")
public class AreaDetailDto {

    @ApiModelProperty(value = "店铺名称")
    private String bName;

    @ApiModelProperty(value = "时间")
    private Date bdsj;

    @ApiModelProperty(value = "善点值")
    private String bdsz;

    @ApiModelProperty(value = "上传人")
    private String xingming;

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }

    public String getBdsz() {
        return bdsz;
    }

    public void setBdsz(String bdsz) {
        this.bdsz = bdsz;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

}
