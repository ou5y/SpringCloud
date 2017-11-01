package com.customer.service.my.impl;

import com.customer.dao.my.RecommendDao;
import com.customer.service.my.RecommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
@Service("recommendService")

public class RecommendServiceImpl implements RecommendService {

    @Autowired
    private RecommendDao recommendDao;

    // 查询角色列表
    public List<Map<String,Object>> queryRoleList(Map<String,Object> params){
        return recommendDao.queryRoleList(params);
    }

    // 查询推荐列表
    @Override
    public PageInfo queryRecommendList(Map<String,Object> params,Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        PageHelper.orderBy("createTime desc");
        return  new PageInfo(recommendDao.queryRecommendList(params));
    }

    // 查询积分明细
    @Override
    public PageInfo queryPointDetail(Map<String,Object> params,Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        PageHelper.orderBy("bdsj desc");
        return new PageInfo(recommendDao.queryPointDetail(params));
    }

}
