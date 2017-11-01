package com.customer.dao.my;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
public interface RecommendDao {

    /**
     * 查询角色列表
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryRoleList(Map<String,Object> params);

    /**
     * 查询会员推荐
     * @param params
     * @return
     */
    List<Map<String,Object>> queryCustomList(Map<String,Object> params);

    /**
     * 查询推荐列表
     * @param params
     * @return
     */
    List<Map<String,Object>> queryRecommendList(Map<String,Object> params);

    /**
     * 查询积分明细
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryPointDetail(Map<String,Object> params);

}
