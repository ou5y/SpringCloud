package com.customer.service;

import com.customer.entity.CIncomeEntity;

import java.util.Map;

/**
 * Created by fangbaoyan on 2017/5/14.
 */
public interface IncomePredicateService {

//    public CIncomeEntity query(Integer id);

    /**
     * 查询积分、主动善点、被动善点明细
     * @param map
     * @return CIncomeEntity
     */
    CIncomeEntity queryIncomeDetail(Map<String,Object> map);

}
