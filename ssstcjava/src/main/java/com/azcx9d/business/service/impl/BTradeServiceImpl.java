package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BTradeDAO;
import com.azcx9d.business.entity.BTrade;
import com.azcx9d.business.service.BTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by HuangQing on 2017/4/3 0003 17:47.
 */
@Service("btradeService")
public class BTradeServiceImpl implements BTradeService {

    @Autowired
    private BTradeDAO dao;

    @Override
    public List<BTrade> selectAll() throws Exception {
        return dao.selectAll();
    }

    @Override
    public BTrade selectById(int id) throws Exception {
        return dao.selectById(id);
    }
}
