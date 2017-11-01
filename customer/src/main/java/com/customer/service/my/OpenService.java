package com.customer.service.my;

import com.customer.dto.OpenLogDto;
import com.customer.dto.my.RecommendLogDto;
import com.customer.util.JsonResult;
import com.customer.util.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public interface OpenService {

    /**
     * 添加开通记录
     * @param params
     * @return int
     */
    JsonResult addOpen(Map<String,Object> params);

    /**
     * 查询开通记录是否已经存在
     * @param params
     * @return int
     */
    int queryExist(Map<String,Object> params);

    /**
     * 查询开通记录
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return List<Map<String,Object>>
     */
    PageInfo openLog(Map<String,Object> params, Integer pageIndex, Integer pageSize);

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
     * 查询全部区域
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryAllAreas(Map<String,Object> params);

    /**
     * 配送关系生效
     * @param params
     * @return JsonResult
     */
    JsonResult savePushAgent(Map<String,Object> params);

    /**
     * 开通记录
     * @param params
     * @return RecommendLogDto
     */
    RecommendLogDto queryRecommendLog(Map<String,Object> params);

    /**
     * 更新关系
     * @param phones
     * @return JsonResult
     */
    JsonResult updateRelationship(String phones,int operatorId);

    /**
     * 查询待开通号码身份
     * @param params
     * @return Map<String,Object>
     */
    JsonResult queryBeOpenedUser(Map<String,Object> params);

    /**
     * 查询开通记录
     * @param params
     * @return List<OpenLogDto>
     */
    Page queryOperatorLog(Map<String,Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 查询配送记录
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return
     */
    Page queryPushAgentList(Map<String,Object> params, Integer pageIndex, Integer pageSize);

}
