package com.customer.dto;

import com.customer.entity.CIncomeEntity;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by fangbaoyan on 2017/5/17.
 */
@ApiModel("我的推荐善点")
public class MyRecommendPointDto extends CIncomeEntity {

    @ApiModelProperty(value = "推荐奖励记录")
    private PageInfo<MyRecommendPointRecordDto> recode;


    public PageInfo<MyRecommendPointRecordDto> getRecode() {
        return recode;
    }

    public void setRecode(PageInfo<MyRecommendPointRecordDto> recode) {
        this.recode = recode;
    }


}
