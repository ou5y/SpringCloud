package com.azcx9d.business.service;

import com.azcx9d.business.dto.my.UserCenterNotice;
import com.azcx9d.common.util.Page;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/6 0006 09:46.
 */
public interface BSystemMessageService {

    /**
     * 查询系统消息，分页
     * @param page
     * @param paraMap
     */
    Page selectMessageList(Page page, HashMap<String, Object> paraMap) throws Exception;

    /**
     * 查询用户中心通知
     * @param params
     * @return UserCenterNotice
     */
    UserCenterNotice queryUserCenterNotice(Map<String,Object> params);
}
