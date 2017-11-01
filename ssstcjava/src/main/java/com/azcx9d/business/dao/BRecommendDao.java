package com.azcx9d.business.dao;

import com.azcx9d.common.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public interface BRecommendDao {

    /**
     * 查询角色列表
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryRoleList(Map<String,Object> params);

    /**
     * 计算总条数
     * @param params
     * @return int
     */
    int countRecommendList(Map<String,Object> params);

    /**
     * 查询推荐列表
     * @param params
     * @return Page
     */
    List<Map<String,Object>> queryRecommendList(Map<String,Object> params);

    /**
     * 计算总条数
     * @param params
     * @return int
     */
    int countPointDetail(Map<String,Object> params);

    /**
     * 查询积分明细
     * @param params
     * @return Page
     */
    List<Map<String,Object>> queryPointDetail(Map<String,Object> params);

}
