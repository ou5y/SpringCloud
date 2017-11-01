package com.azcx9d.mybatisgenerator.dao;

import com.azcx9d.business.dto.CheckBusinessDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.mybatisgenerator.model.OrderForm;
import com.azcx9d.mybatisgenerator.model.OrderFormCriteria;
import com.azcx9d.mybatisgenerator.model.OrderFormKey;
import java.util.List;

public interface OrderFormMapper {
    int deleteByPrimaryKey(OrderFormKey key);

    int insert(OrderForm record);

    int insertSelective(OrderForm record);

    List<OrderForm> selectByExample(OrderFormCriteria example);

    OrderForm selectByPrimaryKey(OrderFormKey key);

    int updateByPrimaryKeySelective(OrderForm record);

    int updateByPrimaryKey(OrderForm record);

    CheckBusinessDto checkStoreById(ParaMap paraMap);

    String getOrderSum(ParaMap paraMap);
}