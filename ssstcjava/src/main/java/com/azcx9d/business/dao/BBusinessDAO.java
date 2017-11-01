package com.azcx9d.business.dao;

import com.azcx9d.business.dto.BusinessManageDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/3 0003 09:48.
 */
public interface BBusinessDAO {

    List selectAll();

    BusinessManageDto selectByBusinessId(int businessId);

    int updateByBusinessId(ParaMap paraMap);
}
