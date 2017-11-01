package com.customer.service;

import com.customer.dto.statistic.AreaAgencyDto;
import com.customer.dto.statistic.IncomeStatisticDto;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface StatisticService {

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
     * 区域代理收益
     * @param params
     * @return AreaAgencyDto
     */
    AreaAgencyDto queryAreaStatistic(Map<String,Object> params);

//    /**
//     * 行业代理收益
//     * @param params
//     * @return AreaAgencyDto
//     */
//    AreaAgencyDto queryTradeStatistic(Map<String,Object> params);

    /**
     * 查询区域收益明细
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryAreaDetail(Map<String,Object> params,int currentPage,int pageSize);

    /**
     * 查询行业收益明细
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryTradeDetail(Map<String,Object> params,int currentPage,int pageSize);

}
