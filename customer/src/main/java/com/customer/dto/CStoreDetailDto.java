package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/9 0009.
 */
@ApiModel(value = "商铺详情")
public class CStoreDetailDto {
    @ApiModelProperty(value = "商铺id")
    private long id;
    @ApiModelProperty(value = "名称")
    private String bName;
    @ApiModelProperty(value = "地址")
    private String businessAddress;
    @ApiModelProperty(value = "图片")
    private String businessPhoto;
    @ApiModelProperty(value = "掌柜")
    private String legalperson;
    @ApiModelProperty(value = "联系电话")
    private String legalpersonNum;
    @ApiModelProperty(value = "开店时间")
    private Date uploadDate;
    @ApiModelProperty(value = "店铺属性")
    private String operateType;
    @ApiModelProperty(value = "星级")
    private String star;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getBusinessPhoto() {
        return businessPhoto;
    }

    public void setBusinessPhoto(String businessPhoto) {
        this.businessPhoto = businessPhoto;
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

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }
}
