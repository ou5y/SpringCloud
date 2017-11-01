package com.customer.dto;

import com.customer.entity.CIncomeEntity;
import com.customer.entity.CIncomeRecodEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/5/14.
 */
@ApiModel("善点记录")
public class CMyShandianRecode extends CIncomeRecodEntity {

    @ApiModelProperty("善点")
    private String shandian;

    @ApiModelProperty("商铺名称")
    private String bName;

    @ApiModelProperty("线额,0=默认 1=小额度 2=中额度 3=大额度")
    private int quotaType;

    public String getShandian() {
        return shandian;
    }

    public void setShandian(String shandian) {
        this.shandian = shandian;
    }

    public String getbName() {
        return bName;
    }

    public void setbName(String bName) {
        this.bName = bName;
    }

    public int getQuotaType() {
        return quotaType;
    }

    public void setQuotaType(int quotaType) {
        this.quotaType = quotaType;
    }

}
