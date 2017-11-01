package com.azcx9d.system.dao;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.system.entity.SSystemUser;
import org.apache.ibatis.annotations.Select;

/**
 * Created by HuangQing on 2017/4/6 0006 16:36.
 */
public interface SSystemUserDAO {

    @Select("select * from sys_user where username=#{username}")
    SSystemUser selectByUsername(ParaMap paraMap) throws Exception;

    @Select("select * from sys_user where id=#{userId}")
    SSystemUser selectById(ParaMap paraMap) throws Exception;
}
