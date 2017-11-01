package com.azcx9d.business.service;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/2 0002.
 */
public interface BSecondKillService {

    /**
     * 我要做秒杀
     * @param params
     * @return int
     */
    int addSecondKillPlan(Map<String,Object> params);

}
