package com.azcx9d.business.service;

import com.azcx9d.business.dto.CheckBusinessDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.consumer.entity.OrderEntity;
import com.azcx9d.mybatisgenerator.model.OrderForm;

/**
 * Created by fangbaoyan on 2017/6/10.
 */
public interface OrderService {

    int createOrder(OrderForm orderForm);

    CheckBusinessDto checkStoreById(ParaMap paraMap);

    String getOrderSum(ParaMap paraMap);

}
