package com.customer.service.my;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
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
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return PageInfo
     */
    PageInfo queryRecommendList(Map<String,Object> params,Integer pageIndex, Integer pageSize);

    /**
     * 查询积分明细
     * @param params
     * @return PageInfo
     */
    PageInfo queryPointDetail(Map<String,Object> params,Integer pageIndex, Integer pageSize);

}
