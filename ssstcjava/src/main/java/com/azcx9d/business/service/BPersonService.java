package com.azcx9d.business.service;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.util.Page;

import java.util.Map;

/**
 * Created by chenxl on 2017/5/24 0024.
 */
public interface BPersonService  {

    Map getMyIntegral(ParaMap paraMap) throws Exception;

    Map getMyShandian(ParaMap paraMap) throws Exception;

    Map getMyRecommendShandian(ParaMap paraMap) throws Exception;

    Map queryMyIntegral(ParaMap paraMap) throws Exception;

    Map queryMyShandian(ParaMap paraMap) throws Exception;

    Map queryPassiveShandian(ParaMap paraMap) throws Exception;

}
