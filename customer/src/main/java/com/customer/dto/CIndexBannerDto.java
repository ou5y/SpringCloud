package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/5/9 0009.
 */
@ApiModel(value = "banner信息")
public class CIndexBannerDto {
    @ApiModelProperty(value = "id")
    private int id;
    @ApiModelProperty(value = "名称")
    private String name;
    @ApiModelProperty(value = "图片")
    private String pic;
    @ApiModelProperty(value = "url链接")
    private String url;

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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
