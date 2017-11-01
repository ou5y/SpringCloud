package com.customer.service.impl;

import com.customer.dao.StatisticDao;
import com.customer.dto.AgencyBenefitsDto;
import com.customer.dto.statistic.AreaAgencyDto;
import com.customer.dto.statistic.IncomeDetailDto;
import com.customer.dto.statistic.IncomeStatisticDto;
import com.customer.service.StatisticService;
import com.customer.util.Arith;
import com.customer.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Service("statisticsService")

public class StatisticsServiceImpl implements StatisticService {

    @Autowired
    private StatisticDao statisticDao;

    //今日数据
    @Override
    public Map<String,String> queryTotalDatas(){
        return statisticDao.queryTotalDatas();
    }

    //开启今日数据
    @Override
    public Map<String,String> queryTodayData(){
        return statisticDao.queryTodayData();
    }

    /**
     * 区域代理收益
     * @param params
     * @return AreaAgencyDto
     */
    public AreaAgencyDto queryAreaStatistic(Map<String,Object> params){
        AreaAgencyDto areaAgencyDto = new AreaAgencyDto();
        List<AgencyBenefitsDto> all = new ArrayList<AgencyBenefitsDto>(0);
        Double total = 0.0;
        Object areaId = params.get("areaId");
        params.put("tradeId",Integer.parseInt(params.get("tradeId").toString()));
        List<Map<String,Object>> allAreas = statisticDao.queryAllAreas(params);
        for(Map<String,Object> area: allAreas){
            AgencyBenefitsDto agencyBenefitsDto = new AgencyBenefitsDto();
            agencyBenefitsDto.setAreaId(Integer.parseInt(area.get("areaId").toString()));
            agencyBenefitsDto.setAreaName(area.get("fullName").toString());
            agencyBenefitsDto.setTradeName(area.get("tradeName").toString());
            agencyBenefitsDto.setTradeId(Integer.parseInt(area.get("tradeId").toString()));

            String tempCode = area.get("areaId").toString();
            Integer tradeId = Integer.valueOf(area.get("tradeId").toString());
            genericParams(params,tempCode,tradeId);
            params.put("areaId",tempCode);
            params.put("tradeId",area.get("tradeId"));
            params.put("parentCode",tempCode);
            Map<String,Object> countMap = statisticDao.queryAgencyStatistics(params);
            agencyBenefitsDto.setTotal(countMap.get("total").toString());
            total = Arith.add(total,Double.parseDouble(countMap.get("total").toString()));
            all.add(agencyBenefitsDto);
        }
        areaAgencyDto.setTotal(total.toString());
        areaAgencyDto.setList(all);
        return areaAgencyDto;
    }

    /**
     * 构造参数
     * @param params
     * @param tempCode
     * @param tradeId
     */
    private void genericParams(Map<String,Object> params,String tempCode,Integer tradeId){
        if(tempCode.substring(2,6).equals("0000")){
            params.put("areaLevel",0);
            if(tradeId>0){
                params.put("grantType",3);
                params.put("fromType",4);
            }else{
                params.put("grantType",2);
                params.put("fromType",1);
            }
        }else if(tempCode.substring(4,6).equals("00")){
            params.put("areaLevel",1);
            if(tradeId>0){
                params.put("grantType",3);
                params.put("fromType",5);
            }else{
                params.put("grantType",2);
                params.put("fromType",2);
            }
        }else{
            params.put("areaLevel",2);
            if(tradeId>0){
                params.put("grantType",3);
                params.put("fromType",5);
            }else{
                params.put("grantType",2);
                params.put("fromType",3);
            }
        }
    }


    // 查询区域收益明细
    @Override
    public Map<String,Object> queryAreaDetail(Map<String,Object> params,int currentPage,int pageSize){
        Map<String,Object> result = new HashMap<String,Object>(0);
        Page page = new Page(currentPage,pageSize);

        String tempCode = params.get("areaId").toString();
        Integer tradeId = 0;
        genericParams(params,tempCode,tradeId);

        int total = statisticDao.countAreaDetail(params);
        String totalAmount = statisticDao.countAreaAll(params);         //总收益
        page.setTotal(total);
        int offSet = page.getOffset();
        params.put("offSet",offSet);
        params.put("pageSize",pageSize);
        List<Map<String,Object>> list = statisticDao.queryAreaDetail(params);
        page.setList(list);
        page.init();
        result.put("page",page);
        result.put("total",totalAmount);
        return result;
    }

    // 查询行业收益明细
    @Override
    public Map<String,Object> queryTradeDetail(Map<String,Object> params,int currentPage,int pageSize){
        Map<String,Object> result = new HashMap<String,Object>(0);
        Page page = new Page(currentPage,pageSize);

        String tempCode = params.get("areaId").toString();
        Integer tradeId = Integer.valueOf(params.get("tradeId").toString());
        genericParams(params,tempCode,tradeId);

        int total = statisticDao.countTradeDetail(params);          //查询总记录数
        String totalAmount = statisticDao.countTradeAll(params);    //行业总收益
        page.setTotal(total);
        int offSet = page.getOffset();
        params.put("offSet",offSet);
        params.put("pageSize",pageSize);
        List<Map<String,Object>> list = statisticDao.queryTradeDetail(params);          //设置当前页数结果集
        page.setList(list);
        page.init();
        result.put("page",page);
        result.put("total",totalAmount);
        return result;
    }

}
