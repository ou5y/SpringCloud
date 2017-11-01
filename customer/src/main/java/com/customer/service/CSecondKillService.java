package com.customer.service;

import com.customer.entity.CSecondKill;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
public interface CSecondKillService {

    /**
     * 根据id删除秒杀活动
     * @param id
     * @return int
     */
    int deleteById(Integer id);

    /**
     *保存秒杀活动
     * @param params
     * @return int
     */
    int addCSecondKill(Map<String,Object> params);

    /**
     * 查询活动详情
     * @param id
     * @return CSecondKill
     */
    CSecondKill selectById(Integer id);

    /**
     * 分页查询秒杀活动
     * @return
     */
    PageInfo<Map<String,Object>> queryCSecondKill(Map<String,Object> params);

    /**
     * 修改秒杀活动
     * @param params
     * @return int
     */
    int updateByPrimaryKey(Map<String,Object> params);

}
