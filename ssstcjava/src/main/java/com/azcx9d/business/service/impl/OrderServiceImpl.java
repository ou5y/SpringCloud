package com.azcx9d.business.service.impl;

import com.azcx9d.business.dto.CheckBusinessDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.OrderService;
import com.azcx9d.consumer.entity.OrderEntity;
import com.azcx9d.mybatisgenerator.dao.OrderFormMapper;
import com.azcx9d.mybatisgenerator.model.OrderForm;
import com.azcx9d.pay.entity.builder.NbpayTradePayRequestBuilder;
import com.azcx9d.pay.service.PaySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by fangbaoyan on 2017/6/10.
 */
@Service("orderServiceImpl")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderFormMapper orderFormMapper;

    @Override
    public int createOrder(OrderForm orderForm) {

        orderFormMapper.insertSelective(orderForm);



        return 1;

    }

    @Override
    public CheckBusinessDto checkStoreById(ParaMap paraMap) {
        return orderFormMapper.checkStoreById(paraMap);
    }

    @Override
    public String getOrderSum(ParaMap paraMap) {
        return orderFormMapper.getOrderSum(paraMap);
    }
}
