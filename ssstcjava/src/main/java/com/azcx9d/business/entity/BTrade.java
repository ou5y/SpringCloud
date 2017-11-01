package com.azcx9d.business.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by HuangQing on 2017/4/3 0003 17:42.
 */
@ApiModel
public class BTrade {

    @ApiModelProperty("分类id")
    private int id;

    @ApiModelProperty("分类名字")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
