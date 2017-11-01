package com.azcx9d.consumer.service.impl;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.common.util.Page;
import com.azcx9d.consumer.dao.ConsumptionDao;
import com.azcx9d.consumer.entity.BusinessEntity;
import com.azcx9d.consumer.entity.OrderEntity;
import com.azcx9d.consumer.service.ConsumptionService;
import com.azcx9d.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by chenxl on 2017/3/31 0031.
 */
@Service("consumptionService")
public class ConsumptionServiceImpl implements ConsumptionService {
    @Autowired
    private TokenManagerDao tokenDao;

    @Autowired
    private ConsumptionDao dao;

    @Override
    public JsonResult createOrder(long userId, long storeId, double money) throws Exception {
        BusinessEntity businessEntity = dao.checkStoreById(storeId);
        if(null == businessEntity){
            return new JsonResult(2,"商户ID不正确,请重新输入!");
        }
        if(money<1){
            return new JsonResult(2,"消费金额不能低于1元");
        }
        //商家当天订单额度
        double orderSum = dao.getOrderSum(storeId);
        double maxAmount = businessEntity.getMaxAmount().setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        if((orderSum+money)>maxAmount){
            return new JsonResult(2,"下单失败!商户今日的剩余消费额度:"+((maxAmount-orderSum)>0?(maxAmount-orderSum):0.0)+"元");
        }

        OrderEntity order = new OrderEntity();
        order.setOrderId("SS"+(System.currentTimeMillis()+"").substring(3)+((int)((Math.random()*9+1)*100)));
        order.setUserId(userId);
        order.setStoreId(storeId);
        order.setMoney(money);
        order.setSellerId(businessEntity.getUserId());
        int count = dao.insertOrder(order);
        if(count > 0){
            return new JsonResult(0,"下单成功!");
        }else{
            return new JsonResult(1,"下单失败!请重新试试");
        }
    }

    @Override
    public JsonResult getOrderList(long userId) throws Exception {
        List<OrderEntity> orderList = dao.getOrderList(userId);
        return new JsonResult(0,"查询成功!", orderList);
    }

    @Override
    public Page getOrderByState(Map<String, Object> params, Page page) throws Exception {
        int totalRow = dao.CountGetOrderByState(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<OrderEntity> orderList = dao.getOrderByState(params);
        page.setPageList(orderList);
        return page;
    }

    @Override
    public Page getClosedOrder(Map<String, Object> params, Page page) throws Exception {
        int totalRow = dao.CountClosedOrder(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<OrderEntity> orderList = dao.getClosedOrder(params);
        page.setPageList(orderList);
        return page;
    }
}
