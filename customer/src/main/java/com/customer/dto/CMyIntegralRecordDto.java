package com.customer.dto;

import com.customer.entity.CIncomeRecodEntity;
import com.customer.web.CIncomeController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * Created by fangbaoyan on 2017/5/13.
 */
@Api(value = "积分记录",description = "积分记录")
public class CMyIntegralRecordDto extends CIncomeRecodEntity{



    @ApiModelProperty(value = "积分")
    private String value;

    @ApiModelProperty(value = "让利")
    private String rangli;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRangli() {
        return rangli;
    }

    public void setRangli(String rangli) {
        this.rangli = rangli;
    }

}
