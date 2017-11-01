package com.customer.dto.statistic;

import com.customer.dto.AgencyBenefitsDto;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/8 0008.
 */
@ApiModel(value = "区域行业代理收益")
public class AreaAgencyDto {

    @ApiModelProperty(value = "历史总收益")
    private String total;

    @ApiModelProperty(value = "分页数据")
    private List<AgencyBenefitsDto> list = new ArrayList<AgencyBenefitsDto>(0);

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public List<AgencyBenefitsDto> getList() {
        return list;
    }

    public void setList(List<AgencyBenefitsDto> list) {
        this.list = list;
    }

}
