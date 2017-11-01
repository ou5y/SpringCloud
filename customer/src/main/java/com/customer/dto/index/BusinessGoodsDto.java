package com.customer.dto.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/6/5 0005.
 */
@ApiModel(value = "店铺下的商品信息")
public class BusinessGoodsDto {

    @ApiModelProperty(value = "商品id")
    private Integer id;

    @ApiModelProperty(value = "商品名称")
    private String name;

    @ApiModelProperty(value = "商品主图")
    private String firstPic;

    @ApiModelProperty(value = "价格")
    private String price;

    @ApiModelProperty(value = "折扣价")
    private String shoppingPrice;

    @ApiModelProperty(value = "已售数量")
    private Integer salesNum;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
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

}
