package com.customer.dao;

import com.customer.dto.AreaDetailDto;
import com.customer.dto.statistic.AreaAgencyDto;
import com.customer.dto.statistic.IncomeDetailDto;
import com.customer.dto.statistic.IncomeStatisticDto;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface StatisticDao {

    /**
     * 今日数据
     * @return
     */
    Map<String,String> queryTotalDatas();

    /**
     * 开启今日数据
     * @return
     */
    Map<String,String> queryTodayData();

    /**
     * 查询全部区域
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryAllAreas(Map<String,Object> params);

    /**
     * 代理业务统计
     * @param map
     * @return Map<String,Object>
     */
    Map<String,Object> queryAgencyStatistics(Map<String,Object> map);

    /**
     * 查询区域收益总记录数
     * @param params
     * @return Integer
     */
    Integer countAreaDetail(Map<String,Object> params);

    /**
     * 区域总收益
     * @param params
     * @return
     */
    String countAreaAll(Map<String,Object> params);

    /**
     * 查询区域收益明细
     * @param params
     * @return Map<String,Object>
     */
    List<Map<String,Object>> queryAreaDetail(Map<String,Object> params);

    /**
     * 查询行业收益总记录数
     * @param params
     * @return Integer
     */
    Integer countTradeDetail(Map<String,Object> params);

    /**
     * 行业总收益
     * @param params
     * @return
     */
    String countTradeAll(Map<String,Object> params);

    /**
     * 查询行业收益明细
     * @param params
     * @return Map<String,Object>
     */
    List<Map<String,Object>> queryTradeDetail(Map<String,Object> params);

}
