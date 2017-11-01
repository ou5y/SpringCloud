package com.customer.service.my;

import com.customer.dto.IncomeChangeDto;
import com.customer.dto.IncomeDetailDto;
import com.customer.dto.IncomeTotalDto;
import com.customer.dto.IncomeUserDto;
import com.customer.entity.ParaMap;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by chenxl on 2017/5/16 0016.
 */
public interface MyBusinessService {

    List<IncomeTotalDto> getIncomeTotalList(ParaMap paraMap) throws Exception;

    List<IncomeChangeDto> getIncomeChangeList(ParaMap paraMap) throws Exception;

    PageInfo<IncomeUserDto> getIncomeUserList(ParaMap paraMap) throws Exception;

    PageInfo<IncomeDetailDto> getIncomeDetailList(ParaMap paraMap) throws Exception;
}
