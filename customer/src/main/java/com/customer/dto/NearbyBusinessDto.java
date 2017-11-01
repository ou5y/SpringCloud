package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/6/7 0007.
 */
@ApiModel("附近商家")
public class NearbyBusinessDto{

    @ApiModelProperty(value = "店铺id")
    private Integer id;

    @ApiModelProperty(value = "店铺名称")
    private String bName;

    @ApiModelProperty(value = "行业类型")
    private String operateName;

    @ApiModelProperty(value = "经度")
    private String longitude1;

    @ApiModelProperty(value = "纬度")
    private String latitude1;

    @ApiModelProperty(value = "店铺地址")
    private String address;

    @ApiModelProperty(value = "距离")
    private int distance;

    @ApiModelProperty(value = "店铺图片")
    private String storePhotos;

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

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getLongitude1() {
        return longitude1;
    }

    public void setLongitude1(String longitude1) {
        this.longitude1 = longitude1;
    }

    public String getLatitude1() {
        return latitude1;
    }

    public void setLatitude1(String latitude1) {
        this.latitude1 = latitude1;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getStorePhotos() {
        return storePhotos;
    }

    public void setStorePhotos(String storePhotos) {
        this.storePhotos = storePhotos;
    }

}
