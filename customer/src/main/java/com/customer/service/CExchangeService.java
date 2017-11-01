package com.customer.service;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.github.pagehelper.PageInfo;

/**
 * Created by HuangQing on 2017/5/13 0013 14:57.
 */
public interface CExchangeService {

    CIsExchangeDto getIsExchange(ParaMap paraMap) throws Exception;

    CTotalExchangeDto getTotalExchange(ParaMap paraMap) throws Exception;

    PageInfo<CExchangeRecordDto> getExchangeList(ParaMap paraMap) throws Exception;

    CExchangeDetailDto getExchangeDetail(ParaMap paraMap) throws Exception;

    CExchangeShandianDto toExchange(ParaMap paraMap) throws Exception;

    CExchangeMoneyDto getRealMoney(ParaMap paraMap) throws Exception;

    CExchangeBankcardDto getBankDetail(ParaMap paraMap) throws Exception;

    CExchangedShandianDto getCurrentShandian(ParaMap paraMap) throws Exception;

    CExchangedShandianDto saveExchange(ParaMap paraMap) throws Exception;

}
