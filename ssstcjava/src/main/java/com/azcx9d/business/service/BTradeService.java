package com.azcx9d.business.service;

import com.azcx9d.business.entity.BTrade;

import java.util.List;

/**
 * Created by HuangQing on 2017/4/3 0003 17:46.
 */
public interface BTradeService {

    List<BTrade> selectAll() throws Exception;

    BTrade selectById(int id) throws Exception;
}
