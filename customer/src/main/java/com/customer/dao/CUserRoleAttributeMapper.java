package com.customer.dao;

import com.customer.entity.CUserRoleAttribute;
import java.util.List;
import java.util.Map;

public interface CUserRoleAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CUserRoleAttribute record);

    CUserRoleAttribute selectByPrimaryKey(Integer id);

    List<CUserRoleAttribute> selectAll();

    int updateByPrimaryKey(CUserRoleAttribute record);

    /**
     * 注册送积分
     * @param record
     * @return
     */
    int saveCUserIntegral(CUserRoleAttribute record);

    /**
     * 注册送善点
     * @param record
     * @return
     */
    int saveCUserShandian(CUserRoleAttribute record);

    /**
     * 注册送再消分
     * @param record
     * @return
     */
    int saveCUserReusePoint(CUserRoleAttribute record);

    int updateUserIntegral(Map<String,Object> params);

    int updateUserShandian(Map<String,Object> params);

    int updateUserReusePoint(Map<String,Object> params);

    /**
     * 查询推荐人id
     * @param userId
     * @return
     */
    Map<String,Object> queryRecommendId(Integer userId);

    /**
     * 添加推荐人积分变动记录
     * @param params
     * @return
     */
    int addRecommChangeRecord(Map<String,Object> params);

    /**
     * 添加注册用户积分变动记录
     * @param params
     * @return
     */
    int addChangeRecord(Map<String,Object> params);

}