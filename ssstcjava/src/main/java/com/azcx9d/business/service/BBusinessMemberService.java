package com.azcx9d.business.service;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.util.Page;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by HuangQing on 2017/4/2 0002 17:23.
 */
public interface BBusinessMemberService {

    /**
     * 分页查询我的会员
     * @param page
     * @param paraMap
     */
    Page selectMyMembersByUserId(Page page, ParaMap paraMap) throws Exception;
}
