package com.customer.dto;

import com.customer.entity.CIncomeEntity;
import com.customer.entity.CIncomeRecodEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by chenxl on 2017/5/11 0011.
 */
@ApiModel(value = "我的积分")
public class CMyIntegralDto extends CIncomeEntity{
    @ApiModelProperty(value = "积分记录")
    private PageInfo<CMyIntegralRecordDto> recode;

    public PageInfo<CMyIntegralRecordDto> getRecode() {
        return recode;
    }

    public void setRecode(PageInfo<CMyIntegralRecordDto> recode) {
        this.recode = recode;
    }
}
