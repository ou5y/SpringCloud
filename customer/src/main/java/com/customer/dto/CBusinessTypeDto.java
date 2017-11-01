package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/9 0009.
 */
@ApiModel(value = "商家经营类型")
public class CBusinessTypeDto {
    @ApiModelProperty(value = "类型id")
    private long id;
    @ApiModelProperty(value = "类型名称")
    private String name;

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
}
