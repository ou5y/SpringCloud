package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/29 0029.
 */
@ApiModel(value = "逛一逛商品")
public class HomePageGoodsDto extends AroundGoodsDto {

    @ApiModelProperty(value = "距离")
    private Integer distance;

    @ApiModelProperty(value = "纬度")
    private String latitude1;

    @ApiModelProperty(value = "经度")
    private String longitude1;

    @ApiModelProperty(value = "行业排序")
    private Integer sort;

    public Integer getDistance() {
        return distance;
    }

    public void setDistance(Integer distance) {
        this.distance = distance;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
