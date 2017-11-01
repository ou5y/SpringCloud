package com.azcx9d.business.dao;

import com.azcx9d.business.dto.BusinessMemberDto;
import com.azcx9d.business.entity.ParaMap;

import java.util.List;

/**
 * Created by HuangQing on 2017/4/2 0002 17:29.
 */
public interface BBusinessMemberDAO {

    int countTotal(ParaMap paraMap);

    List<BusinessMemberDto> selectMyMembersByUserId(ParaMap paraMap);
}
