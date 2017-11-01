package com.azcx9d.business.dao;

import com.azcx9d.business.dto.MyPointDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/24 0024.
 */
public interface BPersonDao {

    Map countMyIntegral(ParaMap paraMap);

    List<MyPointDto> getMyIntegral(ParaMap paraMap);

    Map countMyShandian(ParaMap paraMap);

    List<MyPointDto> getMyShandian(ParaMap paraMap);

    Map countMyRecommendShandian(ParaMap paraMap);

    List<MyPointDto> getMyRecommendShandian(ParaMap paraMap);

}
