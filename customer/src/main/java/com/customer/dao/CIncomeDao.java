package com.customer.dao;

import com.customer.dto.*;
import com.customer.entity.ParaMap;

import java.util.List;

/**
 * Created by chenxl on 2017/7/7 0007.
 */
public interface CIncomeDao {

    List<CTypeIncomeDto> getTotalIncome(ParaMap paraMap);

    CRecommendIncomeListDto getRecommendIncomeTotal(ParaMap paraMap);

    List<CRecommendIncomeDto> getRecommendIncomeList(ParaMap paraMap);

    List<CRecommendIncomeUserDto> getRecommendIncomeUser(ParaMap paraMap);

    CBusinessIncomeListDto getBusinessIncomeTotal(ParaMap paraMap);

    List<CBusinessIncomeDto> getBusinessIncomeList(ParaMap paraMap);

    List<CBusinessIncomeUserDto> getBusinessIncomeUser(ParaMap paraMap);

    CDistributionIncomeListDto getDistributionIncomeTotal(ParaMap paraMap);

    List<CDistributionIncomeDto> getDistributionIncomeList(ParaMap paraMap);

    List<CDistributionIncomeUserDto> getDistributionIncomeUser(ParaMap paraMap);
}
