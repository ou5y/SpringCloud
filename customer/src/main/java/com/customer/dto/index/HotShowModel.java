package com.customer.dto.index;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/6/6.
 */
@ApiModel("热门")
public class HotShowModel {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("图片")
    private String firstPic;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("价格")
    private String price;
    @ApiModelProperty("折扣价")
    private String shoppingPrice;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
}
