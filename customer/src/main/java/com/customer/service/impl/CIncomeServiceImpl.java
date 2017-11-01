package com.customer.service.impl;

import com.customer.dao.CIncomeDao;
import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.service.CIncomeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/7/7 0007.
 */
@Service
public class CIncomeServiceImpl implements CIncomeService {

    @Autowired
    private CIncomeDao cIncomeDao;

    @Override
    public CTotalIncomeDto getTotalIncome(ParaMap paraMap) throws Exception {
        CTotalIncomeDto cTotalIncomeDto = new CTotalIncomeDto();
        BigDecimal totalIncome = new BigDecimal("0");
        BigDecimal areaIncome = new BigDecimal("0");
        BigDecimal recommendIncome = new BigDecimal("0");
        BigDecimal businessIncome = new BigDecimal("0");
        BigDecimal tradeIncome = new BigDecimal("0");
        BigDecimal distributionIncome = new BigDecimal("0");
        List<CTypeIncomeDto> list = cIncomeDao.getTotalIncome(paraMap);
        for (CTypeIncomeDto cTypeIncomeDto : list) {
            switch (cTypeIncomeDto.getGrantType()) {
                case "0":
                    recommendIncome = recommendIncome.add(new BigDecimal(cTypeIncomeDto.getTotalIncome()));
                    break;
                case "1":
                    businessIncome = businessIncome.add(new BigDecimal(cTypeIncomeDto.getTotalIncome()));
                    break;
                case "2":
                    areaIncome = areaIncome.add(new BigDecimal(cTypeIncomeDto.getTotalIncome()));
                    break;
                case "3":
                    tradeIncome = tradeIncome.add(new BigDecimal(cTypeIncomeDto.getTotalIncome()));
                    break;
                case "4":
                    distributionIncome = distributionIncome.add(new BigDecimal(cTypeIncomeDto.getTotalIncome()));
                    break;
            }
            totalIncome = totalIncome.add(new BigDecimal(cTypeIncomeDto.getTotalIncome()));
        }
        cTotalIncomeDto.setTotalIncome(totalIncome + "");
        cTotalIncomeDto.setAreaIncome(areaIncome + "");
        cTotalIncomeDto.setRecommendIncome(recommendIncome + "");
        cTotalIncomeDto.setBusinessIncome(businessIncome + "");
        cTotalIncomeDto.setTradeIncome(tradeIncome + "");
        cTotalIncomeDto.setDistributionIncome(distributionIncome + "");
        return cTotalIncomeDto;
    }

    @Override
    public CRecommendIncomeListDto getRecommendIncomeList(ParaMap paraMap) throws Exception {
        CRecommendIncomeListDto cRecommendIncomeListDto = cIncomeDao.getRecommendIncomeTotal(paraMap);
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CRecommendIncomeDto> list = cIncomeDao.getRecommendIncomeList(paraMap);
        cRecommendIncomeListDto.setPage(new PageInfo<CRecommendIncomeDto>(list));
        return cRecommendIncomeListDto;
    }

    @Override
    public PageInfo<CRecommendIncomeUserDto> getRecommendIncomeUser(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CRecommendIncomeUserDto> list = cIncomeDao.getRecommendIncomeUser(paraMap);
        return new PageInfo<CRecommendIncomeUserDto>(list);
    }

    @Override
    public CBusinessIncomeListDto getBusinessIncomeList(ParaMap paraMap) throws Exception {
        CBusinessIncomeListDto cBusinessIncomeListDto = cIncomeDao.getBusinessIncomeTotal(paraMap);
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CBusinessIncomeDto> list = cIncomeDao.getBusinessIncomeList(paraMap);
        cBusinessIncomeListDto.setPage(new PageInfo<CBusinessIncomeDto>(list));
        return cBusinessIncomeListDto;
    }

    @Override
    public PageInfo<CBusinessIncomeUserDto> getBusinessIncomeUser(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CBusinessIncomeUserDto> list = cIncomeDao.getBusinessIncomeUser(paraMap);
        return new PageInfo<CBusinessIncomeUserDto>(list);
    }

    @Override
    public CDistributionIncomeListDto getDistributionIncomeList(ParaMap paraMap) throws Exception {
        CDistributionIncomeListDto cDistributionIncomeListDto = cIncomeDao.getDistributionIncomeTotal(paraMap);
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CDistributionIncomeDto> list = cIncomeDao.getDistributionIncomeList(paraMap);
        cDistributionIncomeListDto.setPage(new PageInfo<CDistributionIncomeDto>(list));
        return cDistributionIncomeListDto;
    }

    @Override
    public PageInfo<CDistributionIncomeUserDto> getDistributionIncomeUser(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CDistributionIncomeUserDto> list = cIncomeDao.getDistributionIncomeUser(paraMap);
        return new PageInfo<CDistributionIncomeUserDto>(list);
    }

    @Override
    public PageInfo<CRecommendIncomeDto> queryRecommendIncome(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CRecommendIncomeDto> list = cIncomeDao.getRecommendIncomeList(paraMap);
        return new PageInfo<CRecommendIncomeDto>(list);
    }

    @Override
    public PageInfo<CBusinessIncomeDto> queryBusinessIncome(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CBusinessIncomeDto> list = cIncomeDao.getBusinessIncomeList(paraMap);
        return new PageInfo<CBusinessIncomeDto>(list);
    }

    @Override
    public PageInfo<CDistributionIncomeDto> queryDistributionIncome(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "ps.id desc");
        List<CDistributionIncomeDto> list = cIncomeDao.getDistributionIncomeList(paraMap);
        return new PageInfo<CDistributionIncomeDto>(list);
    }
}
