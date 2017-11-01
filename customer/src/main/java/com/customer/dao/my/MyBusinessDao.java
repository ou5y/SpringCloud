package com.customer.dao.my;

import com.customer.dto.IncomeChangeDto;
import com.customer.dto.IncomeDetailDto;
import com.customer.dto.IncomeTotalDto;
import com.customer.dto.IncomeUserDto;
import com.customer.entity.ParaMap;

import java.util.List;

/**
 * Created by chenxl on 2017/5/16 0016.
 */
public interface MyBusinessDao {

    List<IncomeTotalDto> getIncomeTotalList(ParaMap paraMap);

    List<IncomeChangeDto> getIncomeChangeList(ParaMap paraMap);

    List<IncomeUserDto> getIncomeUserList(ParaMap paraMap);

    List<IncomeDetailDto> getIncomeDetailList(ParaMap paraMap);
}
