package com.customer.service;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.github.pagehelper.PageInfo;

/**
 * Created by chenxl on 2017/7/7 0007.
 */
public interface CIncomeService {

    CTotalIncomeDto getTotalIncome(ParaMap paraMap) throws Exception;

    CRecommendIncomeListDto getRecommendIncomeList(ParaMap paraMap) throws Exception;

    PageInfo<CRecommendIncomeUserDto> getRecommendIncomeUser(ParaMap paraMap) throws Exception;

    CBusinessIncomeListDto getBusinessIncomeList(ParaMap paraMap) throws Exception;

    PageInfo<CBusinessIncomeUserDto> getBusinessIncomeUser(ParaMap paraMap) throws Exception;

    CDistributionIncomeListDto getDistributionIncomeList(ParaMap paraMap) throws Exception;

    PageInfo<CDistributionIncomeUserDto> getDistributionIncomeUser(ParaMap paraMap) throws Exception;

    PageInfo<CRecommendIncomeDto> queryRecommendIncome(ParaMap paraMap) throws Exception;

    PageInfo<CBusinessIncomeDto> queryBusinessIncome(ParaMap paraMap) throws Exception;

    PageInfo<CDistributionIncomeDto> queryDistributionIncome(ParaMap paraMap) throws Exception;
}
