package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/9 0009.
 */
@ApiModel(value = "商家经营子类型")
public class CBusinessTypeChildDto {
    @ApiModelProperty(value = "类型id")
    private long id;
    @ApiModelProperty(value = "类型名称")
    private String name;
    @ApiModelProperty(value = "父id")
    private long parentId;

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

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }
}
