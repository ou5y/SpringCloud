package com.customer.dto.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/6/5 0005.
 */
@ApiModel(value = "商铺首页详情")
public class BusinessDetailsDto {

    @ApiModelProperty(value = "店铺id(店铺号)")
    private Integer id;

    @ApiModelProperty(value = "店铺名称")
    private String bName;

    @ApiModelProperty(value = "图片")
    private String businessPhoto;

    @ApiModelProperty(value = "地址")
    private String businessAddress;

    @ApiModelProperty(value = "纬度")
    private String latitude1;

    @ApiModelProperty(value = "经度")
    private String longitude1;

    @ApiModelProperty(value = "掌柜名称")
    private String xingming;

    @ApiModelProperty(value = "店铺号码")
    private String phone;

    @ApiModelProperty(value = "店铺属性")
    private String operateType;

    @ApiModelProperty(value = "营业开始时间")
    private String openTime;

    @ApiModelProperty(value = "营业结束时间")
    private String closeTime;

    @ApiModelProperty(value = "店铺介绍")
    private String intro;

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

    public String getBusinessPhoto() {
        return businessPhoto;
    }

    public void setBusinessPhoto(String businessPhoto) {
        this.businessPhoto = businessPhoto;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(String latitude1) {
        this.latitude1 = latitude1;
    }

    public String getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(String longitude1) {
        this.longitude1 = longitude1;
    }

    public String getXingming() {
        return xingming;
    }

    public void setXingming(String xingming) {
        this.xingming = xingming;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
