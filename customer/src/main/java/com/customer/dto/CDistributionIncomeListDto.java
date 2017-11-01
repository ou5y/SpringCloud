package com.customer.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/8 0008.
 */
@ApiModel(value = "CDistributionIncomeListDto",description = "配送收益总额及列表")
public class CDistributionIncomeListDto {

    @ApiModelProperty(value = "收益总额")
    private String total;

    @ApiModelProperty("收益列表")
    private PageInfo<CDistributionIncomeDto> page;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public PageInfo<CDistributionIncomeDto> getPage() {
        return page;
    }

    public void setPage(PageInfo<CDistributionIncomeDto> page) {
        this.page = page;
    }
}
