package com.customer.dao.my;

import com.customer.dto.CMyIntegralRecordDto;
import com.customer.dto.CMyShandianRecode;
import com.customer.dto.MyRecommendPointRecordDto;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7 0007.
 */
public interface IncomePredicateDao {

    /**
     * 历史总收益
     * @param map
     * @return
     */
    Double queryTotalIncome(Map<String,Object> map);

    /**
     * 历史被动善点总收益
     * @param map
     * @return
     */
    Double queryTotalPassiveShandian(Map<String,Object> map);

    /**
     * 查询被动善点明细
     * @param params
     * @return List<MyRecommendPointRecordDto>
     */
    List<MyRecommendPointRecordDto> queryPassiveShandianDetail(Map<String, Object> params);

    /**
     * 查询主动善点明细
     * @param params
     * @return
     */
    List<CMyShandianRecode> queryLovePointRecodeDetail(Map<String, Object> params);

    /**
     * 查询积分明细
     * @param params
     * @return
     */
    List<CMyIntegralRecordDto> queryIntegralRecodeDetail(Map<String, Object> params);

}
