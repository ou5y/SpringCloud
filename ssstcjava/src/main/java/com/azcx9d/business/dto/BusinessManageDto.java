package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/31 0031.
 */
@ApiModel("商铺管理信息")
public class BusinessManageDto {

    @ApiModelProperty(value = "商家id")
    private int id;

    @ApiModelProperty(value = "商家名")
    private String bName;

    @ApiModelProperty(value = "商家经营类型")
    private String operateType;

    @ApiModelProperty(value="商家经营类型对应中文")
    private String operateTypeName;

    @ApiModelProperty(value = "商家照片")
    private String businessPhoto;

    @ApiModelProperty(value = "店铺地址")
    private String businessAddress;

    @ApiModelProperty(value = "店铺展示图，如果有多张图，则用英文半角逗号(,)分割开多张图")
    private String showPics;

    @ApiModelProperty(value = "店铺介绍")
    private String intro;

    @ApiModelProperty(value = "营业时间：起，格式：hh：mm")
    private String openTime;

    @ApiModelProperty(value = "营业时间：止，格式：hh：mm")
    private String closeTime;

    @ApiModelProperty("店铺电话")
    private String phone;

    @ApiModelProperty(value = "店铺纬度")
    private String latitude1;

    @ApiModelProperty(value = "店铺经度")
    private String longitude1;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateTypeName() {
        return operateTypeName;
    }

    public void setOperateTypeName(String operateTypeName) {
        this.operateTypeName = operateTypeName;
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

    public String getShowPics() {
        return showPics;
    }

    public void setShowPics(String showPics) {
        this.showPics = showPics;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}
