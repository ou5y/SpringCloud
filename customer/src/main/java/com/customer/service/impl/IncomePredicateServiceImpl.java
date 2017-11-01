package com.customer.service.impl;

import com.customer.dao.UserDao;
import com.customer.dao.my.IncomePredicateDao;
import com.customer.dto.*;
import com.customer.entity.CIncomeEntity;
import com.customer.exception.CustomerException;
import com.customer.service.IncomePredicateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/7 0007.
 */
@Service("incomePredicateService")
public class IncomePredicateServiceImpl implements IncomePredicateService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private IncomePredicateDao incomePredicateDao;

    // 查询积分、主动善点、被动善点明细
    public CIncomeEntity queryIncomeDetail(Map<String,Object> map){

        Integer type = (Integer) map.get("type");
        UserDto userDto = userDao.findById((int) map.get("id"));

        Double total = incomePredicateDao.queryTotalIncome(map);
        if (type==1) {
            // 积分
            CMyIntegralDto cMyIntegralDto = new CMyIntegralDto();
            cMyIntegralDto.setCurrent(userDto.getIntegral());
            cMyIntegralDto.setTotal(total + "");

            cMyIntegralDto.setRecode(this.queryIntegralRecodeDetail(map));
            return cMyIntegralDto;
        }
        if(type==0){
            //主动善点
            CMyShandianDto cMyShandianDto = new CMyShandianDto();
            cMyShandianDto.setCurrent(userDto.getShandian());
            cMyShandianDto.setTotal(total + "");

            cMyShandianDto.setRecode(this.queryLovePointRecodeDetail(map));
            return cMyShandianDto;
        }

        if(type==2){
            //推荐奖励善点
            total = incomePredicateDao.queryTotalPassiveShandian(map);
            MyRecommendPointDto recommendPointDto = new MyRecommendPointDto();
            recommendPointDto.setCurrent(userDto.getRecommendEarnings());
            recommendPointDto.setTotal(total+"");

            recommendPointDto.setRecode(this.queryPassiveShandianDetail(map));
            return recommendPointDto;
        }
        throw new CustomerException(4,"无数据");
    }

    // 积分
    private PageInfo<CMyIntegralRecordDto> queryIntegralRecodeDetail(Map<String,Object> map){

        List<CMyIntegralRecordDto> list;
        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")), "x.id desc");
        list = incomePredicateDao.queryIntegralRecodeDetail(map);
        PageInfo<CMyIntegralRecordDto> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    // 主动善点
    private PageInfo<CMyShandianRecode> queryLovePointRecodeDetail(Map<String,Object> map){
        List<CMyShandianRecode> list;

        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")), "x.id desc");
        list=incomePredicateDao.queryLovePointRecodeDetail(map);

        PageInfo<CMyShandianRecode> pageInfo=new PageInfo(list);

        return pageInfo;
    }

    // 被动善点
    private PageInfo<MyRecommendPointRecordDto> queryPassiveShandianDetail(Map<String,Object> map){
        List<MyRecommendPointRecordDto>  list;
        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")), "ps.id desc");
        list=incomePredicateDao.queryPassiveShandianDetail(map);
        PageInfo<MyRecommendPointRecordDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

}
