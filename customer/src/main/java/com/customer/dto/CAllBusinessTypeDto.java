package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * Created by chenxl on 2017/5/9 0009.
 */
@ApiModel(value = "所有经营类型")
public class CAllBusinessTypeDto {
    @ApiModelProperty(value = "类型id")
    private long id;
    @ApiModelProperty(value = "类型名称")
    private String name;
    @ApiModelProperty(value = "子类型")
    private List<CBusinessTypeChildDto> child;

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

    public List<CBusinessTypeChildDto> getChild() {
        return child;
    }

    public void setChild(List<CBusinessTypeChildDto> child) {
        this.child = child;
    }
}
