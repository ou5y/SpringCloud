package com.customer.service;

import com.customer.dto.CScoreChangeDto;
import com.customer.entity.ParaMap;
import com.github.pagehelper.PageInfo;

/**
 * Created by chenxl on 2017/5/12 0012.
 */
public interface CScoreChangeService {

    PageInfo<CScoreChangeDto> getJifenList(ParaMap paraMap);

    PageInfo<CScoreChangeDto> getShandianList(ParaMap paraMap);

    PageInfo<CScoreChangeDto> getRecommendShandianList(ParaMap paraMap);
}
