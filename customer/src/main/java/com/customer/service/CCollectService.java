package com.customer.service;

import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */
public interface CCollectService {

    /**
     * 我的收藏
     * @param page
     * @param params
     * @param pageIndex
     * @param pageSize
     * @return Page
     */
    PageInfo queryMyCollect(PageInfo page, Map<String, Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 添加收藏
     * @param params
     * @return
     */
    int addCollect(Map<String, Object> params);

    /**
     * 删除收藏
     * @param params
     * @return
     */
    int deleteCollect(Map<String, Object> params);

}
