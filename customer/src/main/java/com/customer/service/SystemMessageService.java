package com.customer.service;

import com.customer.dto.my.UserCenterNotice;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
public interface SystemMessageService {

    /**
     * 查询消息列表
     * @param params
     * @return int
     */
    PageInfo messageList(Map<String,Object> params,Integer pageIndex,Integer pageSize);

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

}
