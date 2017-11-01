package com.customer.dao;

import com.customer.entity.ShoppingCart;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public interface ShoppingCartDao {

    /**
     * 查询购物车总数
     * @param params
     * @return int
     */
    int countShoppingCart(Map<String,Object> params);

    /**
     * 查询我的购物车
     * @param params
     * @return List<ShoppingCart>
     */
    List<ShoppingCart> queryMyShoppingCart(Map<String,Object> params);

    /**
     * 添加购物车
     * @param params
     * @return
     */
    int addShoppingCart(Map<String,Object> params);

    /**
     * 删除购物车
     * @param params
     * @return
     */
    int deleteShoppingCart(Map<String,Object> params);

    /**
     * 编辑购物车
     * @param params
     * @return
     */
    int editShoppingCart(Map<String,Object> params);

}
