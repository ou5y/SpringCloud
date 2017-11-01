package com.customer.dao;

import com.customer.dto.CScoreChangeDto;
import com.customer.entity.ParaMap;

import java.util.List;

/**
 * Created by chenxl on 2017/5/12 0012.
 */
public interface CScoreChangeMapper {

    List<CScoreChangeDto> getJifenList(ParaMap paraMap);

    List<CScoreChangeDto> getShandianList(ParaMap paraMap);

    List<CScoreChangeDto> getRecommendShandianList(ParaMap paraMap);
}
