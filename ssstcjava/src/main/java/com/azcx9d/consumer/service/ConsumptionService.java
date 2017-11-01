package com.azcx9d.consumer.service;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;

import java.util.Map;

/**
 * Created by chenxl on 2017/3/31 0031.
 */
public interface ConsumptionService {
    /**
     * 我要消费
     * @param userId
     * @param storeId
     * @param money
     * @return
     */
    JsonResult createOrder(long userId, long storeId, double money) throws Exception;

    /**
     * 个人订单
     * @param userId
     * @return
     */
    JsonResult getOrderList(long userId) throws Exception;

    /**
     * 根据订单状态查询订单
     * @param params,page
     * @return
     */
    Page getOrderByState(Map<String, Object> params,Page page) throws Exception;

    /**
     * 关闭订单列表
     * @param params,page
     * @return
     */
    Page getClosedOrder(Map<String, Object> params,Page page) throws Exception;
}
