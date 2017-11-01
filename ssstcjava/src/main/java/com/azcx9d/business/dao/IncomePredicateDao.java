package com.azcx9d.business.dao;

import com.azcx9d.business.dto.MyPointDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7 0007.
 */
public interface IncomePredicateDao {

    /**
     * 计算积分总记录数
     * @param params
     * @return
     */
    Integer countMyIntegral(Map<String,Object> params);

    /**
     * 累计积分
     * @param params
     * @return
     */
    Double countMyIntegralTotal(Map<String,Object> params);

    /**
     * 我的积分
     * @param paraMap
     * @return
     * @throws Exception
     */
    List<MyPointDto> queryMyIntegral(ParaMap paraMap) throws Exception;

    /**
     * 计算主动善点记录数
     * @param params
     * @return
     */
    Integer countMyShandian(Map<String,Object> params);

    /**
     * 累计善点
     * @param params
     * @return
     */
    Double countMyShandianTotal(Map<String,Object> params);

    /**
     * 我的主动善点
     * @param paraMap
     * @return
     * @throws Exception
     */
    List<MyPointDto> queryMyShandian(ParaMap paraMap) throws Exception;

    /**
     * 计算被动善点总记录数
     * @param params
     * @return
     */
    Integer countPassiveShandian(Map<String,Object> params);

    /**
     * 累计被动善点
     * @param params
     * @return
     */
    Double countPassiveShandianTotal(Map<String,Object> params);

    /**
     * 被动善点
     * @param paraMap
     * @return
     * @throws Exception
     */
    List<MyPointDto> queryPassiveShandian(ParaMap paraMap) throws Exception;

}
