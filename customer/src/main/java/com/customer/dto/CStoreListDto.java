package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/9 0009.
 */
@ApiModel(value = "推荐商铺信息")
public class CStoreListDto {
    @ApiModelProperty(value = "商铺id")
    private long id;
    @ApiModelProperty(value = "名称")
    private String bName;
    @ApiModelProperty(value = "地址")
    private String businessAddress;
    @ApiModelProperty(value = "图片")
    private String businessPhoto;
    @ApiModelProperty(value = "标签")
    private String tagsName;
    @ApiModelProperty(value = "星级")
    private String star;
    @ApiModelProperty(value = "距离")
    private String distance;
    @ApiModelProperty(value = "人均消费")
    private String perAverage;
    @ApiModelProperty(value = "纬度")
    private String latitude1;
    @ApiModelProperty(value = "经度")
    private String longitude1;

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

    public String getTagsName() {
        return tagsName;
    }

    public void setTagsName(String tagsName) {
        this.tagsName = tagsName;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getPerAverage() {
        return perAverage;
    }

    public void setPerAverage(String perAverage) {
        this.perAverage = perAverage;
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
