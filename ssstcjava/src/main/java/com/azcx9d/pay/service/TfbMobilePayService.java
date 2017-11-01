package com.azcx9d.pay.service;


import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.pay.entity.Order;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
public interface TfbMobilePayService {

    /**
     * 生成支付宝支付申请
     * @param paramsMap
     * @return JsonResult
     */
    JsonResult createPayApply(TreeMap<String, String> paramsMap) throws Exception;

    /**
     * 关闭订单
     * @param paramsMap
     * @return JsonResult
     */
    JsonResult colseOrder(TreeMap<String, String> paramsMap) throws Exception;

    /**
     * 查询交易状态
     * @param paramsMap
     * @return JsonResult
     */
    JsonResult queryTrade(TreeMap<String, String> paramsMap) throws Exception;

    /**
     * 退款查询
     * @param paramsMap
     * @return JsonResult
     */
    JsonResult queryRefund(TreeMap<String, String> paramsMap) throws Exception;

    /**
     * 退款
     * @param paramsMap
     * @return JsonResult
     */
    JsonResult addRefund(TreeMap<String, String> paramsMap) throws Exception;

    /**
     * 异步通知
     * @return JsonResult
     */
    JsonResult notifyUrl(Map<String, String> params) throws Exception;


    //代付
    //JsonResult createPaySingle(TreeMap<String, String> paramsMap,int payType,BOrderForm order);

    /**
     * 单笔代付结果查询接口
     * @return JsonResult
     */
    JsonResult queryOrderInfo(TreeMap<String, String> paramsMap) throws Exception;

}
