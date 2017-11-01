package com.customer.service.impl;

import com.customer.dao.ShoppingCartDao;
import com.customer.entity.ShoppingCart;
import com.customer.service.CShoppingCartService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@Service("cShoppingCartService")

public class CShoppingCartServiceImpl implements CShoppingCartService{

    @Autowired
    private ShoppingCartDao shoppingCartDao;

    // 我的购物车
    @Override
    public PageInfo queryMyShoppingCart(PageInfo page, Map<String,Object> params, Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex, pageSize);
        List<ShoppingCart> list = shoppingCartDao.queryMyShoppingCart(params);
        return new PageInfo(list);
    }

    // 添加购物车
    @Override
    public int addShoppingCart(Map<String,Object> params){
        return  shoppingCartDao.addShoppingCart(params);
    }

    // 删除购物车
    @Override
    public int deleteShoppingCart(Map<String,Object> params){
        return  shoppingCartDao.deleteShoppingCart(params);
    }

    // 编辑购物车
    @Override
    public int editShoppingCart(Map<String,Object> params){
        return  shoppingCartDao.editShoppingCart(params);
    }

}
