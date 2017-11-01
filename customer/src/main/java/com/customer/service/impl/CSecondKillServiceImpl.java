package com.customer.service.impl;

import com.customer.dao.CSecondKillDao;
import com.customer.entity.CSecondKill;
import com.customer.service.CSecondKillService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
@Service("cSecondKillService")

public class CSecondKillServiceImpl implements CSecondKillService {

    @Autowired
    private CSecondKillDao cSecondKillDao;

    // 根据id删除秒杀活动
    @Override
    public int deleteById(Integer id){
        return cSecondKillDao.deleteById(id);
    }

    //保存秒杀活动
    @Override
    public int addCSecondKill(Map<String,Object> params){
        return  cSecondKillDao.addCSecondKill(params);
    }

    /**
     * 查询活动详情
     * @param id
     * @return CSecondKill
     */
    public CSecondKill selectById(Integer id){
        return  cSecondKillDao.selectById(id);
    }

    /**
     * 分页查询秒杀活动
     * @return
     */
    public PageInfo<Map<String,Object>> queryCSecondKill(Map<String,Object> params){
        List<Map<String,Object>> lists = cSecondKillDao.queryCSecondKill(params);
        return new PageInfo<Map<String,Object>>(lists);
    }

    /**
     * 修改秒杀活动
     * @param params
     * @return int
     */
    public int updateByPrimaryKey(Map<String,Object> params){
        return cSecondKillDao.updateByPrimaryKey(params);
    }

}
