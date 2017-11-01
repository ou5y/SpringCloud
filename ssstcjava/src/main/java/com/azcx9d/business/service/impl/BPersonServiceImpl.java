package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BPersonDao;
import com.azcx9d.business.dao.BUserDAO;
import com.azcx9d.business.dao.IncomePredicateDao;
import com.azcx9d.business.dto.MyPointDto;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BPersonService;
import com.azcx9d.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/24 0024.
 */
@Service
public class BPersonServiceImpl implements BPersonService {

    @Autowired
    private BPersonDao bPersonDao;

    @Autowired
    private BUserDAO businessUserDAO;

    @Autowired
    private IncomePredicateDao incomePredicateDao;

    @Override
    public Map getMyIntegral(ParaMap paraMap) throws Exception {
        Map map = new HashMap();
        Page page = new Page(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""));
        Map counts = bPersonDao.countMyIntegral(paraMap);
        page.setTotalRow(Integer.parseInt(counts.get("totalRow")+""));
        paraMap.put("offset", page.getOffset());
        List<MyPointDto> list = bPersonDao.getMyIntegral(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        BUser bUser = businessUserDAO.selectById(Integer.parseInt(paraMap.get("userId")+""));
        map.put("current", bUser.getJifen()+"");
        map.put("total", counts.get("total")+"");
        return map;
    }

    @Override
    public Map getMyShandian(ParaMap paraMap) throws Exception {
        Map map = new HashMap();
        Page page = new Page(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""));
        Map counts = bPersonDao.countMyShandian(paraMap);
        page.setTotalRow(Integer.parseInt(counts.get("totalRow")+""));
        paraMap.put("offset", page.getOffset());
        List<MyPointDto> list = bPersonDao.getMyShandian(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        BUser bUser = businessUserDAO.selectById(Integer.parseInt(paraMap.get("userId")+""));
        map.put("current", bUser.getShandian()+"");
        map.put("total", counts.get("total")+"");
        return map;
    }

    @Override
    public Map getMyRecommendShandian(ParaMap paraMap) throws Exception {
        Map map = new HashMap();
        Page page = new Page(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""));
        Map counts = bPersonDao.countMyRecommendShandian(paraMap);
        page.setTotalRow(Integer.parseInt(counts.get("totalRow")+""));
        paraMap.put("offset", page.getOffset());
        List<MyPointDto> list = bPersonDao.getMyRecommendShandian(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        BUser bUser = businessUserDAO.selectById(Integer.parseInt(paraMap.get("userId")+""));
        map.put("current", bUser.getShandian2()+"");
        map.put("total", counts.get("total")+"");
        return map;
    }

    // 我的积分
    @Override
    public Map queryMyIntegral(ParaMap paraMap) throws Exception{
        Map map = new HashMap();
        Page page = new Page(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""));
        Integer count = incomePredicateDao.countMyIntegral(paraMap);
        Double total = incomePredicateDao.countMyIntegralTotal(paraMap);
        page.setTotalRow(count);
        paraMap.put("offset", page.getOffset());
        List<MyPointDto> list = incomePredicateDao.queryMyIntegral(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        BUser bUser = businessUserDAO.selectById(Integer.parseInt(paraMap.get("userId")+""));
        map.put("current", bUser.getJifen()+"");
        map.put("total", total+"");
        return map;
    }

    // 我的善点
    @Override
    public Map queryMyShandian(ParaMap paraMap) throws Exception{
        Map map = new HashMap();
        Page page = new Page(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""));
        Integer count = incomePredicateDao.countMyShandian(paraMap);
        Double total = incomePredicateDao.countMyShandianTotal(paraMap);
        page.setTotalRow(count);
        paraMap.put("offset", page.getOffset());
        List<MyPointDto> list = incomePredicateDao.queryMyShandian(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        BUser bUser = businessUserDAO.selectById(Integer.parseInt(paraMap.get("userId")+""));
        map.put("current", bUser.getShandian()+"");
        map.put("total", total+"");
        return map;
    }

    // 被动善点
    @Override
    public Map queryPassiveShandian(ParaMap paraMap) throws Exception{
        Map map = new HashMap();
        Page page = new Page(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""));
        Integer count = incomePredicateDao.countPassiveShandian(paraMap);
        Double total = incomePredicateDao.countPassiveShandianTotal(paraMap);
        page.setTotalRow(count);
        paraMap.put("offset", page.getOffset());
        List<MyPointDto> list = incomePredicateDao.queryPassiveShandian(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        BUser bUser = businessUserDAO.selectById(Integer.parseInt(paraMap.get("userId")+""));
        map.put("current", bUser.getShandian2()+"");
        map.put("total", total+"");
        return map;
    }

}
