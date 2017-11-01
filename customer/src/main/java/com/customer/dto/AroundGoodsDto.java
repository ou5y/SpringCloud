package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/29 0029.
 */
@ApiModel(value = "逛一逛和首页商品")
public class AroundGoodsDto {

    @ApiModelProperty("商品id")
    private Integer id;

    @ApiModelProperty("图片")
    private String firstPic;

    @ApiModelProperty("商品名称")
    private String name;

    @ApiModelProperty("价格")
    private String price;

    @ApiModelProperty(value = "折扣价")
    private String shoppingPrice;

    @ApiModelProperty(value = "地址")
    private String businessAddress;

    @ApiModelProperty("商铺名称")
    private String bName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getShoppingPrice() {
        return shoppingPrice;
    }

    public void setShoppingPrice(String shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
    }

    public String getBusinessAddress() {
        return businessAddress;
    }

    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }
}
