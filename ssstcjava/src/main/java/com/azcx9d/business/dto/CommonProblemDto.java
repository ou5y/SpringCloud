package com.azcx9d.business.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/6/2 0002.
 */
@ApiModel(value = "关于,常见问题",description = "关于,常见问题")
public class CommonProblemDto {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "链接地址")
    private String url;

    @ApiModelProperty(value = "类型(1:关于全团了, 2:操作类问题, 3:商户类问题, 4:身份类问题, 5:银行卡类问题)")
    private int type;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
