package com.customer.service.my.impl;

import com.customer.dao.my.MyBusinessDao;
import com.customer.dto.IncomeChangeDto;
import com.customer.dto.IncomeDetailDto;
import com.customer.dto.IncomeTotalDto;
import com.customer.dto.IncomeUserDto;
import com.customer.entity.ParaMap;
import com.customer.service.my.MyBusinessService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenxl on 2017/5/16 0016.
 */
@Service
public class MyBusinessServiceImpl implements MyBusinessService {

    @Autowired
    private MyBusinessDao myBusinessDao;

    @Override
    public List<IncomeTotalDto> getIncomeTotalList(ParaMap paraMap) throws Exception {
        return myBusinessDao.getIncomeTotalList(paraMap);
    }

    @Override
    public List<IncomeChangeDto> getIncomeChangeList(ParaMap paraMap) throws Exception {
        return myBusinessDao.getIncomeChangeList(paraMap);
    }

    @Override
    public PageInfo<IncomeUserDto> getIncomeUserList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "c.totalIncome desc");
        List<IncomeUserDto> list = myBusinessDao.getIncomeUserList(paraMap);
        return new PageInfo<IncomeUserDto>(list);
    }

    @Override
    public PageInfo<IncomeDetailDto> getIncomeDetailList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "a.bdsj desc");
        List<IncomeDetailDto> list = myBusinessDao.getIncomeDetailList(paraMap);
        return new PageInfo<IncomeDetailDto>(list);
    }
}
