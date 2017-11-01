package com.customer.dao;

import com.customer.dto.RoleListDto;
import com.customer.dto.my.UserCenterNotice;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public interface SystemMessageDao {

    /**
     * 查询消息列表
     * @param params
     * @return int
     */
    List<Map<String,Object>> messageList(Map<String,Object> params);

    /**
     * 消息详情
     * @param params
     * @return Map<String,Object>
     */
    Map<String,Object> messageDetail(Map<String,Object> params);

    /**
     * 查询用户中心通知
     * @param params
     * @return UserCenterNotice
     */
    UserCenterNotice queryUserCenterNotice(Map<String,Object> params);

    /**
     * 查询用户角色列表
     * @param params
     * @return List<RoleListDto>
     */
    List<RoleListDto> queryRoleList(Map<String,Object> params);

    /**
     * 更新用户阅读标识
     * @param params
     * @return int
     */
    int updateReadFlag(Map<String,Object> params);

}
