package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BRecommendDao;
import com.azcx9d.business.service.RecommendService;
import com.azcx9d.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
@Service("recommendService")
public class RecommendServiceImpl implements RecommendService{

    @Autowired
    private BRecommendDao bRecommendDao;

    // 查询角色列表
    @Override
    public List<Map<String,Object>> queryRoleList(Map<String,Object> params){
        return  bRecommendDao.queryRoleList(params);
    }

    // 查询推荐列表
    @Override
    public Page queryRecommendList(Page page, Map<String,Object> params, Integer pageIndex, Integer pageSize){
        int totalRow = bRecommendDao.countRecommendList(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<Map<String,Object>> list = bRecommendDao.queryRecommendList(params);
        page.setPageList(list);
        return page;
    }

    // 查询积分明细
    @Override
    public Page queryPointDetail(Page page,Map<String,Object> params,Integer pageIndex, Integer pageSize){
        int totalRow = bRecommendDao.countPointDetail(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<Map<String,Object>> list = bRecommendDao.queryPointDetail(params);
        page.setPageList(list);
        return page;
    }

}
