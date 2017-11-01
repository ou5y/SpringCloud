package com.customer.dto;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by chenxl on 2017/7/7 0007.
 */
@ApiModel(value = "CRecommendIncomeListDto",description = "推荐奖励收益总额及列表")
public class CRecommendIncomeListDto {

    @ApiModelProperty(value = "收益总额")
    private String total;

    @ApiModelProperty("收益列表")
    private PageInfo<CRecommendIncomeDto> page;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public PageInfo<CRecommendIncomeDto> getPage() {
        return page;
    }

    public void setPage(PageInfo<CRecommendIncomeDto> page) {
        this.page = page;
    }
}
