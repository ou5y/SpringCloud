package com.azcx9d.business.service;

import com.azcx9d.common.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
public interface RecommendService {

    /**
     * 查询角色列表
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryRoleList(Map<String,Object> params);

    /**
     * 查询推荐列表
     * @param page
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return Page
     */
    Page queryRecommendList(Page page,Map<String,Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 查询积分明细
     * @param page
     * @param params
     * @return Page
     */
    Page queryPointDetail(Page page,Map<String,Object> params,Integer pageIndex, Integer pageSize);

}
