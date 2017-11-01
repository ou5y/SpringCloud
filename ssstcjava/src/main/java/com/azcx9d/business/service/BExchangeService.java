package com.azcx9d.business.service;

import com.azcx9d.business.dto.*;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.util.Page;

/**
 * Created by chenxl on 2017/4/7 0007.
 */
public interface BExchangeService {

    IsExchangeDto getIsExchange(ParaMap paraMap) throws Exception;

    TotalExchangeDto getTotalExchange(ParaMap paraMap) throws Exception;

    Page getExchangeList(Page page, ParaMap paraMap) throws Exception;

    ExchangeDetailDto getExchangeDetail(ParaMap paraMap) throws Exception;

    ExchangeShandianDto toExchange(ParaMap paraMap) throws Exception;

    ExchangeMoneyDto getRealMoney(ParaMap paraMap) throws Exception;

    ExchangeBankcardDto getBankDetail(ParaMap paraMap) throws Exception;

    ExchangedShandianDto getCurrentShandian(ParaMap paraMap) throws Exception;

    ExchangedShandianDto saveExchange(ParaMap paraMap) throws Exception;

}
