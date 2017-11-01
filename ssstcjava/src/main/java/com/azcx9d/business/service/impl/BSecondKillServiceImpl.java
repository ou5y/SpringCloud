package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BSecondKillDao;
import com.azcx9d.business.service.BSecondKillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
@Service("bSecondKillService")
public class BSecondKillServiceImpl implements BSecondKillService {

    @Autowired
    private BSecondKillDao bSecondKillDao;

    //我要做秒杀
    @Override
    public int addSecondKillPlan(Map<String,Object> params){
        return bSecondKillDao.addSecondKillPlan(params);
    }
}
