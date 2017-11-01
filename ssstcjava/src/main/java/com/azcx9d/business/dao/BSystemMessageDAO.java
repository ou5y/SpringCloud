package com.azcx9d.business.dao;

import com.azcx9d.business.dto.RoleListDto;
import com.azcx9d.business.dto.my.UserCenterNotice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/6 0006 10:23.
 */
public interface BSystemMessageDAO {

    int countTotal(HashMap<String, Object> paraMap);

    List<Map<String,Object>> selectMessageList(HashMap<String, Object> paraMap);

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
