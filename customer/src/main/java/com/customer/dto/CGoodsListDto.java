package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@ApiModel(value = "商品信息")
public class CGoodsListDto {
    @ApiModelProperty(value = "商品id")
    private long id;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "商铺id")
    private long businessId;
    @ApiModelProperty(value = "商铺名称")
    private String businessName;
    @ApiModelProperty(value = "价格")
    private String price;
    @ApiModelProperty(value = "已售")
    private Integer salesNum;
    @ApiModelProperty(value = "库存")
    private Integer inventoryNum;
    @ApiModelProperty(value = "图片")
    private String firstPic;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(long businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getSalesNum() {
        return salesNum;
    }

    public void setSalesNum(Integer salesNum) {
        this.salesNum = salesNum;
    }

    public Integer getInventoryNum() {
        return inventoryNum;
    }

    public void setInventoryNum(Integer inventoryNum) {
        this.inventoryNum = inventoryNum;
    }

    public String getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(String firstPic) {
        this.firstPic = firstPic;
    }
}
