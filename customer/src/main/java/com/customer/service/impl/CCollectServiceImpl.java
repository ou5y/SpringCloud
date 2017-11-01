package com.customer.service.impl;

import com.customer.dao.CCollectDao;
import com.customer.entity.CCollect;
import com.customer.service.CCollectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
@Service("cCollectService")

public class CCollectServiceImpl implements CCollectService{

    @Autowired
    private CCollectDao cCollectDao;

    // 我的收藏
    @Override
    public PageInfo queryMyCollect(PageInfo page, Map<String,Object> params, Integer pageIndex, Integer pageSize){
        PageHelper.startPage(pageIndex, pageSize);
        List<Map<String,Object>> list = cCollectDao.queryMyCollect(params);
        return new PageInfo(list);
    }

    // 添加收藏
    @Override
    public int addCollect(Map<String,Object> params){
        return cCollectDao.addCollect(params);
    }

    // 删除收藏
    @Override
    public int deleteCollect(Map<String,Object> params){
        return cCollectDao.deleteCollect(params);
    }

}
