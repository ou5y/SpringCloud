package com.customer.dto;

import com.customer.entity.CIncomeEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/11 0011.
 */
@ApiModel(value = "我的善点")
public class CMyShandianDto extends CIncomeEntity {
    @ApiModelProperty("善点记录")
    private PageInfo<CMyShandianRecode> recode;

    public PageInfo<CMyShandianRecode> getRecode() {
        return recode;
    }

    public void setRecode(PageInfo<CMyShandianRecode> recode) {
        this.recode = recode;
    }
}
