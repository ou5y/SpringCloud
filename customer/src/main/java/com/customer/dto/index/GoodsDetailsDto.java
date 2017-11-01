package com.customer.dto.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/6/5 0005.
 */
@ApiModel(value = "商品详情")
public class GoodsDetailsDto {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "店铺id")
    private Integer businessId;

    @ApiModelProperty(value = "轮播图片(3张)")
    private String loopPics;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "折扣价")
    private String shoppingPrice;

    @ApiModelProperty(value = "销量")
    private Integer salesNum;

    @ApiModelProperty(value = "产品参数")
    private String params;

    @ApiModelProperty(value = "商品详情")
    private String detail;

    @ApiModelProperty(value = "展示图片(9张)")
    private String showPics;

    @ApiModelProperty(value = "让利类型")
    private String rangli;

    @ApiModelProperty(value = "店铺信息")
    private BusinessDetailsDto businessDetailsDto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoopPics() {
        return loopPics;
    }

    public void setLoopPics(String loopPics) {
        this.loopPics = loopPics;
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

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getShowPics() {
        return showPics;
    }

    public void setShowPics(String showPics) {
        this.showPics = showPics;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public BusinessDetailsDto getBusinessDetailsDto() {
        return businessDetailsDto;
    }

    public void setBusinessDetailsDto(BusinessDetailsDto businessDetailsDto) {
        this.businessDetailsDto = businessDetailsDto;
    }
}
