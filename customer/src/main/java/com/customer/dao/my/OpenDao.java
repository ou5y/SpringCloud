package com.customer.dao.my;

import com.customer.dto.OpenLogDto;
import com.customer.dto.RecommendStatusDto;
import com.customer.dto.my.RecommendLogDto;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public interface OpenDao {

    /**
     * 添加开通记录
     * @param params
     * @return int
     */
    int addOpen(Map<String,Object> params);

    /**
     * 查询开通记录是否已经存在
     * @param params
     * @return int
     */
    int queryExist(Map<String,Object> params);

    /**
     * 查询被推荐号码是否已经存在
     * @param params
     * @return
     */
    int queryExistRecommended(Map<String,Object> params);

    /**
     * 查询角色是否已经存在
     * @param params
     * @return int
     */
    int queryExistRole(Map<String,Object> params);

    /**
     * 查询推荐人parentId
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryParentId(Map<String,Object> params);

    /**
     * 更新推荐关系
     * @param params
     * @return int
     */
    int updateRecommendRelation(Map<String,Object> params);

    /**
     * 更新平台角色
     * @param params
     * @return int
     */
    int updatePlatformStatus(Map<String,Object> params);

    /**
     * 查询Platform记录是否存在
     * @param params
     * @return int
     */
    int queryPlatform(Map<String,Object> params);

    /**
     * 添加PlatformStatus
     * @param params
     * @return int
     */
    int addPlatformStatus(Map<String,Object> params);

    /**
     * 查询开通记录
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> openLog(Map<String,Object> params);

    /**
     * 查询推荐人是否存在
     * @param params
     * @return int
     */
    int queryRecommendMan(Map<String,Object> params);

    /**
     * 查询被推荐人是否存在
     * @param params
     * @return int
     */
    int queryRecommendedMan(Map<String,Object> params);

    /**
     * 查询Platform_status
     * @param params
     * @return int
     */
    int queryPlatformStatus(Map<String,Object> params);

    /**
     * 查询IdentityId
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryIdentityId(Map<String,Object> params);

    /**
     * 查询开通上限/已开通数量
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryOpenLimit(Map<String,Object> params);

    /**
     * 查询推荐人身份
     * @param params
     * @return RecommendStatusDto
     */
    RecommendStatusDto queryRecommendStatus(Map<String,Object> params);

    /**
     * 查询被推荐人身份
     * @param params
     * @return RecommendStatusDto
     */
    RecommendStatusDto queryRecommendedStatus(Map<String,Object> params);

    /**
     * 查询配送到的用户的身份
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryAgencyUserStatus(Map<String,Object> params);

    /**
     * 查询待开通号码身份
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryBeOpenedUserStatus(Map<String,Object> params);

    /**
     * 查询待开通号码 userId
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryBeOpenedUserId(Map<String,Object> params);

    /**
     * 待开通推荐人身份
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> queryRecommendUserStatus(Map<String,Object> params);

    /**
     * 查询待开通号码 推荐人的parentId 的 DirectlyId
     * @param params
     * @return Integer
     */
    Integer queryDirectlyId(Map<String,Object> params);

    /**
     * 查询配送银牌
     * @param params
     * @return
     */
    int queryExistPushAgent(Map<String,Object> params);

    /**
     * 保存配送银牌
     * @param params
     * @return addPushAgent
     */
    int addPushAgent(Map<String,Object> params);

    /**
     * 开通记录
     * @param params
     * @return RecommendLogDto
     */
    RecommendLogDto queryRecommendLog(Map<String,Object> params);

    /**
     * 批量更新操作者
     * @param params
     * @return
     */
    int updateOperatorId(Map<String,Object> params);

    /**
     * 查询待开通号码身份
     * @param params
     * @return Map<String,Object>
     */
    List<Map<String,Object>> queryBeOpenedUser(Map<String,Object> params);

    /**
     * 查询总记录数
     * @param params
     * @return
     */
    int countOperatorLog(Map<String,Object> params);

    /**
     * 查询开通记录
     * @param params
     * @return List<OpenLogDto>
     */
    List<Map<String,Object>> queryOperatorLog(Map<String,Object> params);

    /**
     * 查询已经开通配送银牌数
     * @param params
     * @return
     */
    int countPushAgentByOperator(Map<String,Object> params);

    /**
     * 计算配送记录总条数
     * @param params
     * @return int
     */
    int countPushAgentList(Map<String,Object> params);

    /**
     *
     * @param params
     * @return
     */
    List<Map<String,Object>> queryPushAgentList(Map<String,Object> params);

}
