package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/10 0011.
 */
@ApiModel(value = "CUserAddressDto",description = "收货地址")
public class CUserAddressDto {

    @ApiModelProperty(value = "地址id")
    private int id;

    @ApiModelProperty(value = "用户id")
    private int userId;

    @ApiModelProperty(value = "收货人姓名")
    private String name;

    @ApiModelProperty(value = "收货人手机号码")
    private String phone;

    @ApiModelProperty(value = "省")
    private int province;

    @ApiModelProperty(value = "市")
    private int city;

    @ApiModelProperty(value = "区")
    private int area;

    @ApiModelProperty(value = "省(汉字)")
    private String provinceDesc;

    @ApiModelProperty(value = "市(汉字)")
    private String cityDesc;

    @ApiModelProperty(value = "区(汉字)")
    private String areaDesc;

    @ApiModelProperty(value = "详细地址(街道,楼牌号等)")
    private String fullAddress;

    @ApiModelProperty(value = "默认地址(0:不是默认地址, 1:默认地址)")
    private int isDefault;

    public CUserAddressDto() {
    }

    public CUserAddressDto(int id, int userId, String name, String phone, int province, int city, int area, String fullAddress, int isDefault) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.province = province;
        this.city = city;
        this.area = area;
        this.fullAddress = fullAddress;
        this.isDefault = isDefault;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getProvince() {
        return province;
    }

    public void setProvince(int province) {
        this.province = province;
    }

    public int getCity() {
        return city;
    }

    public void setCity(int city) {
        this.city = city;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getProvinceDesc() {
        return provinceDesc;
    }

    public void setProvinceDesc(String provinceDesc) {
        this.provinceDesc = provinceDesc;
    }

    public String getCityDesc() {
        return cityDesc;
    }

    public void setCityDesc(String cityDesc) {
        this.cityDesc = cityDesc;
    }

    public String getAreaDesc() {
        return areaDesc;
    }

    public void setAreaDesc(String areaDesc) {
        this.areaDesc = areaDesc;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }
}
