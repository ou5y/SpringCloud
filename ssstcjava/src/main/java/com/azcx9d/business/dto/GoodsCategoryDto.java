package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/27 0027.
 */
@ApiModel("商品类型")
public class GoodsCategoryDto {

    @ApiModelProperty(value = "类型id")
    private Integer id;

    @ApiModelProperty(value = "类型名称")
    private String name;

    @ApiModelProperty(value = "类型父id")
    private Integer parentId;

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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
