package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/7/17 0016.
 */
@ApiModel(value = "商家详情")
public class TeamManageBusinessDto {

    @ApiModelProperty(value = "商家名称(店铺名称)")
    private String bName;

    @ApiModelProperty(value = "法人姓名")
    private String legalperson;

    @ApiModelProperty(value = "法人手机号")
    private String legalpersonNum;

    @ApiModelProperty(value = "上传时间")
    private Date uploadDate;

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getLegalperson() {
        return legalperson;
    }

    public void setLegalperson(String legalperson) {
        this.legalperson = legalperson;
    }

    public String getLegalpersonNum() {
        return legalpersonNum;
    }

    public void setLegalpersonNum(String legalpersonNum) {
        this.legalpersonNum = legalpersonNum;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
