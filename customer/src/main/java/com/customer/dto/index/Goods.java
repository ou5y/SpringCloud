package com.customer.dto.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/6/3.
 */
@ApiModel("商品")
public class Goods {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("图片")
    private String firstPic;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("价格")
    private String price;
    @ApiModelProperty("已购买人数")
    private String salesNum;
    @ApiModelProperty(value = "折扣价")
    private String shoppingPrice;


    public String getId() {
        return id;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getSalesNum() {
        return salesNum;
    }

    public String getShoppingPrice() {
        return shoppingPrice;
    }

    public Goods setId(String id)
    {
        this.id=id;
        return this;
    }

    public Goods setFirstPic(String firstPic)
    {
        this.firstPic = firstPic;
        return this;
    }

    public Goods setName(String name)
    {
        this.name = name;
        return this;
    }

    public Goods setPrice(String price)
    {
        this.price = price;
        return this;
    }

    public Goods setSalesNum(String salesNum) {
        this.salesNum = salesNum;
        return this;
    }

    public Goods setShoppingPrice(String shoppingPrice) {
        this.shoppingPrice = shoppingPrice;
        return this;
    }
}
