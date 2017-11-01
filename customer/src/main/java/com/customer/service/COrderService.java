package com.customer.service;

import com.customer.dto.COrderListDto;
import com.customer.dto.OrderTotalDto;
import com.customer.dto.ScanBusinessDto;
import com.customer.entity.COrder;
import com.customer.entity.ParaMap;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by chenxl on 2017/5/8.
 */
public interface COrderService {
    /**
     * 我要消费
     * @param paraMap
     * @return
     */
//    int createOrder(ParaMap paraMap) throws Exception;

    /**
     * 订单总额
     * @param paraMap
     * @return
     */
    OrderTotalDto getOrderTotal(ParaMap paraMap) throws Exception;

    /**
     * 待奖励订单列表
     * @param paraMap
     * @return
     */
    PageInfo<COrderListDto> getStartOrder(ParaMap paraMap) throws Exception;

    /**
     * 已完结订单列表
     * @param paraMap
     * @return
     */
    PageInfo<COrderListDto> getEndOrder(ParaMap paraMap) throws Exception;

    /**
     * 已关闭订单列表
     * @param paraMap
     * @return
     */
    PageInfo<COrderListDto> getClosedOrder(ParaMap paraMap) throws Exception;

    /**
     * 网络支付订单总额
     * @param paraMap
     * @return
     */
    OrderTotalDto getNetWorkOrderTotal(ParaMap paraMap) throws Exception;

    /**
     * 网络支付待付款订单列表
     * @param paraMap
     * @return
     */
    PageInfo<COrderListDto> getStartNetWorkOrder(ParaMap paraMap) throws Exception;

    /**
     * 网络支付已完结订单列表
     * @param paraMap
     * @return
     */
    PageInfo<COrderListDto> getEndNetWorkOrder(ParaMap paraMap) throws Exception;

    /**
     * 网络支付已关闭订单列表
     * @param paraMap
     * @return
     */
    PageInfo<COrderListDto> getClosedNetWorkOrder(ParaMap paraMap) throws Exception;

    PageInfo<COrderListDto> getSuccessNetWorkOrder(ParaMap paraMap) throws Exception;

    int saveFreeOrder(ParaMap paraMap) throws Exception;

    ScanBusinessDto scanBusiness(ParaMap paraMap) throws Exception;
}
