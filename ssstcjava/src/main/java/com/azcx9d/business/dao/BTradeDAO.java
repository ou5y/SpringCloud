package com.azcx9d.business.dao;

import com.azcx9d.business.entity.BTrade;

import java.util.List;

/**
 * Created by HuangQing on 2017/4/3 0003 17:44.
 */
public interface BTradeDAO {

    List<BTrade> selectAll();

    BTrade selectById(int id);
}
