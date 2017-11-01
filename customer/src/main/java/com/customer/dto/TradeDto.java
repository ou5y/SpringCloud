package com.customer.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@ApiModel(value = "经营类型")
public class TradeDto {

    @ApiModelProperty(value = "主键id")
    private long id;

    @ApiModelProperty(value = "分类名称")
    private String name;

    @ApiModelProperty(value = "让利比例")
    private String rangli;

    @ApiModelProperty(value = "经营类型，-1=全部 0=低额 1=小额度 2=中额度 3=大额度")
    private int quotaType;

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

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

}
