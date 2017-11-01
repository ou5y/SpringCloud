package com.customer.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public interface CShoppingCartService {

    /**
     * 我的购物车
     * @param page
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return Page
     */
    PageInfo queryMyShoppingCart(PageInfo page, Map<String, Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 添加购物车
     * @param params
     * @return
     */
    int addShoppingCart(Map<String, Object> params);

    /**
     * 删除购物车
     * @param params
     * @return
     */
    int deleteShoppingCart(Map<String, Object> params);

    /**
     * 编辑购物车
     * @param params
     * @return
     */
    int editShoppingCart(Map<String, Object> params);

}
