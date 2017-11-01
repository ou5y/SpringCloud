package com.azcx9d.pay.service;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.pay.entity.builder.NbpayTradeQueryRequestBuilder;
import com.azcx9d.pay.service.impl.NbpayF2FPayResult;
import com.azcx9d.pay.entity.builder.NbpayTradePayRequestBuilder;
import com.azcx9d.pay.service.impl.NbpayF2FTradeQueryResult;
import com.azcx9d.pay.util.ApiException;

import java.io.IOException;
import java.util.Map;


/**
 * Created by fangbaoyan on 2017/4/17.
 */


public interface PaySerivce {

    NbpayF2FPayResult tradePay(NbpayTradePayRequestBuilder builder);

    /**
     * 交易查询
     * @param builder
     * @return JsonResult
     */
    JsonResult tradeQuery(NbpayTradeQueryRequestBuilder builder);

    /**
     * 消费结果异步通知
     * @param params
     * @return JsonResult
     */
    JsonResult updatePayNotify(Map<String,String> params);

    int updateOrderSettlement(final int orderId);

}
