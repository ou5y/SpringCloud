package com.azcx9d.user.dao;

import com.azcx9d.user.entity.Notice;

import java.util.Map;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
public interface AppDao {

    /**
     * 查找公告
     * @param params
     * @return
     */
    Notice findByParams(Map<String,Object> params);

}
