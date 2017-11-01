package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/6/2 0002.
 */
@ApiModel(value = "扫一扫商家信息")
public class ScanBusinessDto {

    @ApiModelProperty(value = "商家名")
    private String bName;

    @ApiModelProperty(value = "商家照片")
    private String businessPhoto;

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
}
