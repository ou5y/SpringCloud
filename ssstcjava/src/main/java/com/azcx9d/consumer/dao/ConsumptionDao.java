package com.azcx9d.consumer.dao;

import com.azcx9d.consumer.entity.BusinessEntity;
import com.azcx9d.consumer.entity.OrderEntity;
import com.azcx9d.user.entity.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/3/31 0031.
 */
public interface ConsumptionDao {

    BusinessEntity checkStoreById(long storeId) throws SQLException;

    int insertOrder(OrderEntity order) throws SQLException;

    List<OrderEntity> getOrderList(long userId) throws SQLException;

    List<OrderEntity> getOrderByState(Map<String, Object> params) throws Exception;

    int CountGetOrderByState(Map<String, Object> params) throws Exception;

    double getOrderSum(long storeId) throws SQLException;

    int CountClosedOrder(Map<String, Object> params) throws Exception;

    List<OrderEntity> getClosedOrder(Map<String, Object> params) throws Exception;
}
