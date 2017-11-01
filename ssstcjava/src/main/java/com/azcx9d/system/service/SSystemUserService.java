package com.azcx9d.system.service;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.system.entity.SSystemUser;

/**
 * Created by HuangQing on 2017/4/6 0006 16:35.
 */
public interface SSystemUserService {

    /**
     * 根据用户名查询用户
     * @param paraMap 包含 username ： 用户名
     * @return 数据库中的用户对象
     */
    SSystemUser selectByUsername(ParaMap paraMap) throws Exception;

    /**
     * 通过userId查询对应的系统用户
     * @param paraMap
     * @return
     */
    SSystemUser selectById(ParaMap paraMap) throws Exception;
}
