package com.azcx9d.business.service;

import com.azcx9d.business.dto.BusinessManageDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/3 0003 09:47.
 */
public interface BBusinessService {

//    List selectAll() throws Exception;

    /**
     * 根据店主id查询店铺详细信息
     * @param businessId 店铺店主用户的id
     * @return
     */
    BusinessManageDto selectByBusinessId(int businessId) throws Exception;

    /**
     * 编辑店铺详细信息
     * @param paraMap
     */
    int updateByBusinessId(ParaMap paraMap);
}
